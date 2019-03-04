import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

class AStarProject{
    public static void main(String[] args){
        int choice = 0;
        while(choice < 1 || choice > 3){
            System.out.println("A* search menu\n1. Enter 8-puzzle\n2. Solve Random 8-puzzle\n3. Generate Stats for 8-puzzle\n");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
        }
        if (choice == 1){
            enter_test();
        }
        else if (choice == 2){
            solve_random_test();
        }
        else if (choice==3){
            run_metrics();
        }

    }

    public static int[] gen_random_test(){
        int[] valid_test = new int[9];
        boolean is_valid = false;
        do{
            int[] test = new int[9];
            int count = 0;
            Set<Integer> set = new HashSet<Integer>();
            while(count!=9){
                Random rand = new Random();
                int random_int = rand.nextInt(10);
                if (!set.contains((Integer) random_int)){
                    test[count] = random_int;
                    count++;
                    set.add((Integer) random_int);
                }
            }
            if (is_valid(test)){
                valid_test = test;
                is_valid = true;
            }
        }while(!is_valid);

        return valid_test;
    }

    public static void solve_random_test(){
        int[] test = gen_random_test();
        Node initial1 = new Node(test, null, 0, 1);
        Node initial2 = new Node(test, null, 0, 1);
        AStar search1 = new AStar(initial1);
        AStar search2 = new AStar(initial2);
        float start_time1 = search1.timer_ms();
        Node solution1 = search1.search_for_solution();
        float end_time1 = search1.timer_ms();

        System.out.println("Solution using heuristic 1");
        print(solution1);
        System.out.println("\nSolution Depth, Search Cost, Run Time (ms): " + Integer.toString(solution1.depth) + ", " + Integer.toString(search1.num_nodes_gen) + ", " + Float.toString(end_time1 - start_time1));

        float start_time2 = search2.timer_ms();
        Node solution2 = search2. search_for_solution();
        float end_time2 = search2.timer_ms();

        System.out.println("Solution using heuristic 2");
        print(solution2);
        System.out.println("\nSolution Depth, Search Cost, Run Time (ms): " + Integer.toString(solution2.depth) + ", " + Integer.toString(search2.num_nodes_gen) + ", " + Float.toString(end_time2 - start_time2)); 
    }

    public static void enter_test(){
        boolean board_valid = false;
        int[] valid_board = new int[9];
        do{
            System.out.print("Enter Board (Format: 0 1 2 3 4 5 6 7 8):");
            Scanner sc = new Scanner(System.in);
            String board = sc.nextLine();
            int[] board_value = new int[9];
            int count = 0;
            for (int i = 0; i<board.length(); i++){
                if (i%2 == 0){
                    char value = board.charAt(i);
                    board_value[count] = Character.getNumericValue(value);
                    count++;
                }
            }
            if (is_valid(board_value)){
                board_valid = true;
                valid_board = board_value;
            }
            else{
                System.out.println("Board is not Valid. Please reinput");
            }
        }while(!board_valid);
        Node initial1 = new Node(valid_board, null, 0, 1);
        Node initial2 = new Node(valid_board, null, 0, 2);
        AStar search1 = new AStar(initial1);
        AStar search2 = new AStar(initial2);
        float start_time1 = search1.timer_ms();
        Node solution1 = search1.search_for_solution();
        float end_time1 = search1.timer_ms();

        System.out.println("Solution using heuristic 1");
        print(solution1);
        System.out.println("\nSolution Depth, Search Cost, Run Time (ms): " + Integer.toString(solution1.depth) + ", " + Integer.toString(search1.num_nodes_gen) + ", " + Float.toString(end_time1 - start_time1));

        float start_time2 = search2.timer_ms();
        Node solution2 = search2. search_for_solution();
        float end_time2 = search2.timer_ms();

        System.out.println("Solution using heuristic 2");
        print(solution2);
        System.out.println("\nSolution Depth, Search Cost, Run Time (ms): " + Integer.toString(solution2.depth) + ", " + Integer.toString(search2.num_nodes_gen) + ", " + Float.toString(end_time2 - start_time2));


    }

    public static void print(Node solution_node){
        ArrayList<Node> solution = new ArrayList<Node>();
        solution.add(solution_node);
        Node parent_node = solution_node.parent_node;
        while(parent_node != null){
            solution.add(parent_node);
            parent_node = parent_node.parent_node;
        }
        for (int i=0; i<solution.size(); i++){
            System.out.println("\nStep " + Integer.toString(i+1) + ":");
            solution.get(solution.size() - i - 1).print_board();
        }

    }

    public static boolean is_valid(int[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] < 0 || board[i] >= board.length) {
                return false;
            }
    
         for (int j = 0; j < i; j++) {
             if (board[i] == board[j]) {
                 return false;
             }
         }
        }
        if (!is_solvable(board)) {
            return false;
        }
        return true;
  }

    public static boolean is_solvable (int[] board) {
        boolean solvable = true;
        for (int i = 0; i < 8; i++) {
            if(board[i] == 0 || board[i] == 1) {
                continue;
            }
            for (int j = i + 1; j < 9; j++) {
                if ((board[i] > board[j]) && (board[j] != 0)) {
                    solvable = !solvable;
                }
            }
        }
        return solvable;
    }


    public static void run_metrics(){
        int[] depth1 = new int[100];
        int[] depth2 = new int[100];
        int[] num_nodes1 = new int[100];
        int[] num_nodes2 = new int[100];
        float[] run_time_per_depth1 = new float[100];
        float[] run_time_per_depth2 = new float[100];
        float[] search_cost_per_depth1 = new float[100];
        float[] search_cost_per_depth2 = new float[100];

        for (int i = 0; i < 100; i++){ 
            System.out.println("Running Test: " + Integer.toString(i));
            int[] test = gen_random_test();
            Node h1 = new Node(test, null, 0, 1);
            Node h2 = new Node(test, null, 0, 2);
            AStar search1 = new AStar(h1);
            AStar search2 = new AStar(h2);

            float start_time1 = search1.timer_ms();
            Node solution1 = search1.search_for_solution();
            float end_time1 = search1.timer_ms();
            float total_time1 = end_time1 - start_time1;
            float start_time2 = search2.timer_ms();
            Node solution2 = search2.search_for_solution();
            float end_time2 = search2.timer_ms();
            float total_time2 = end_time2 - start_time2;
            num_nodes1[i] = search1.num_nodes_gen;
            num_nodes2[i] = search2.num_nodes_gen;
            depth1[i] = solution1.depth;
            depth2[i] = solution2.depth;
            run_time_per_depth1[i] = total_time1 / (float) depth1[i];
            run_time_per_depth2[i] = total_time2/ (float) depth2[i];
            search_cost_per_depth1[i] = (float) num_nodes1[i] / (float) depth1[i];
            search_cost_per_depth2[i] = (float) num_nodes2[i] / (float) depth2[i];
        }

        System.out.println("A* Search Costs for h1");
        for (int i =2; i<25;i++){
            int total_nodes = 0;
            int num_tests = 0;
            float total_run_time_per_depth = 0;
            float total_search_cost_per_depth = 0;
            for (int j =0; j<1000; j++){
                if (depth1[j] == i){
                    num_tests++;
                    total_nodes += num_nodes1[j];
                    total_run_time_per_depth += run_time_per_depth1[j];
                    total_search_cost_per_depth += search_cost_per_depth1[j];
                }
            }
            
            System.out.println("\nDepth, Average Nodes, Average Search Cost/Depth, Average Run Time (ms)/Depth: " + Integer.toString(i) + ", " + Float.toString(total_nodes/num_tests) + ", " + Float.toString(total_search_cost_per_depth/num_tests) + ", " + Float.toString(total_run_time_per_depth/num_tests));
        }

        System.out.println("A* Search Costs for h2");
        for (int i =2; i<25;i++){
            int total_nodes = 0;
            int num_tests = 0;
            float total_run_time_per_depth = 0;
            float total_search_cost_per_depth = 0;
            for (int j =0; j<1000; j++){
                if (depth2[j] == i){
                    num_tests++;
                    total_nodes += num_nodes2[j];
                    total_run_time_per_depth += run_time_per_depth2[j];
                    total_search_cost_per_depth += search_cost_per_depth2[j];
                }
            }
            
            System.out.println("Depth, Average Nodes, Average Search Cost/Depth, Average Run Time/Depth: " + Integer.toString(i) + ", " + Float.toString(total_nodes/num_tests) + ", " + Float.toString(total_search_cost_per_depth/num_tests) + ", " + Float.toString(total_run_time_per_depth/num_tests));
        }
    }
}
