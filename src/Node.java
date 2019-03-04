public class Node{
    private int[] board; //Current State
    private static final int length = 3; //Length of Board
    private static final int width = 3; //Width of Board
    
    public Node parent_node; //Node from which this state is generated from
    public int depth; //Which depth the tree is generated from
    public int cost; //f(n) = Path-cost - g(n) + heuristic - h(n)
    public int mode;

    // heurstic mode: 1 = heurstic1 2 = heurstic2
    public Node(int[] board, Node parent_node, int depth, int heuristic_mode){
        this.board = board; //Current State
        this.depth = depth; //Number of Moves Made
        this.parent_node = parent_node;
        this.mode = heuristic_mode;
        if (heuristic_mode == 1){
            this.cost = set_cost1();
        }
        else if (heuristic_mode == 2){
            this.cost = set_cost2();
        }
        else{
            throw new Error("Unknown Heuristic Mode");
        }
    }

    //calculate_cost
    
    public int set_cost1(){
        return this.depth + calc_heuristic1();
    }

    public int set_cost2(){
        return this.depth + calc_heuristic2();
    }

    public int get_cost(){
        return this.cost;
    }
    // check_similar_node
    public boolean is_node_similar(Node unknown_node){
        int[] unknown_board = unknown_node.get_board();
        int count = 0;
        for (int i =0; i<9; i++){
            if (unknown_board[i] == this.board[i]){
                count++;
            }
        }
        if (count == 9){
            return true;
        }
        return false;
    }

    public int[] get_board(){
        return this.board;
    }

    private int get_index_blank_tile(){
        for (int i = 0; i < 9; i++){
            if (board[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public Node move_up(){
        //System.out.println("up");
        int idx_blank = get_index_blank_tile();
        if (idx_blank == -1){
            throw new Error("Not valid board");
        }
        Pair location = new Pair(idx_blank);
        int row = location.get_row();
        //Cannot move blank up because it is in the top row
        if (row == 0){
            return null;
        }
        
        int new_row_loc = row - 1;
        Pair new_row_location = new Pair(new_row_loc, location.get_col());
        int new_idx = new_row_location.get_idx();

        int[] new_board = swap(this.board, new_idx, idx_blank);

        return new Node(new_board, this, this.depth + 1, this.mode);
    }
    
    public Node move_down(){
        //System.out.println("down");
        int idx_blank = get_index_blank_tile();
        if (idx_blank == -1){
            throw new Error("Not valid board");
        }
        Pair location = new Pair(idx_blank);
        int row = location.get_row();
        if (row >= length - 1){
            return null;
        }
        
        int new_row_loc = row + 1;
        Pair new_location = new Pair(new_row_loc, location.get_col());
        int new_idx = new_location.get_idx();

        int[] new_board = swap(this.board, new_idx, idx_blank);

        return new Node(new_board, this, this.depth + 1, this.mode);
    }

    public Node move_right(){
        //System.out.println("right");
        int idx_blank = get_index_blank_tile();
        if (idx_blank == -1){
            throw new Error("Not valid board");
        }
        Pair location = new Pair(idx_blank);
        int col = location.get_col();
        if (col >= width - 1){
            return null;
        }

        int new_col_loc = col + 1;
        Pair new_location = new Pair(location.get_row(), new_col_loc);
        int new_idx = new_location.get_idx();
        int[] new_board = swap(this.board, new_idx, idx_blank);

        return new Node(new_board, this, this.depth + 1, this.mode);
    }
    
    public Node move_left(){
        //System.out.println("left");
        int idx_blank = get_index_blank_tile();
        if (idx_blank == -1){
            throw new Error("Not valid board");
        }
        Pair location = new Pair(idx_blank);
        int col = location.get_col();
        if (col == 0){
            return null;
        }

        int new_col_loc = col - 1;
        Pair new_location = new Pair(location.get_row(), new_col_loc);
        int new_idx = new_location.get_idx();
        int[] new_board = swap(this.board, new_idx, idx_blank);

        return new Node(new_board, this, this.depth + 1, this.mode);
    }

    private int[] swap(int[] arr, int loc_1, int loc_2){
        int temp = arr[loc_1];
        int[] new_arr = arr.clone();
        new_arr[loc_1] = arr[loc_2];
        new_arr[loc_2] = temp;
        return new_arr;
    }

    public int calc_heuristic1(){
        int count = 0;
        for (int i = 0; i < 9; i++){
            if (this.board[i] != i){
                count++;
            }
        }
        return count;
    }
    
    public int calc_heuristic2(){
        int count = 0;
        for (int i = 0; i < 9; i++){
            if (this.board[i] != i){
                Pair cur_loc = new Pair(i);
                Pair goal_loc = new Pair(this.board[i]);
                int dist_from_goal_col = Math.abs(cur_loc.get_col() - goal_loc.get_col());
                int dist_from_goal_row = Math.abs(cur_loc.get_row() - goal_loc.get_row());
                count += (dist_from_goal_col + dist_from_goal_row);
            }
        }
        return count;
    }

    public boolean is_goal(){
        for (int i = 0; i<9; i++){
            if (this.board[i] != i){
                return false;
            }
        }
        return true;
    }

    public void print_board(){
        for (int i=0; i<9; i++){
            if (i%3==0){
                System.out.print("\n");
            }
            System.out.print(Integer.toString(this.board[i]) + " ");
        }
    }
}

