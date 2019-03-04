import java.util.ArrayList;

class AStar{
    public Node initial_node;
    public FrontierPriorityQueue frontier;
    public ExploredSet explored;
    public int num_nodes_gen;

    AStar(Node start){
        this.initial_node = start;
        this.frontier = new FrontierPriorityQueue();
        this.explored = new ExploredSet();
        this.num_nodes_gen = 0;
    }

    public long timer_ms(){
        return System.nanoTime() / 1000000; 
    }

    public Node search_for_solution(){
        Node solution = null;
        frontier.add(this.initial_node);
        boolean solution_found = false;
        while(!solution_found){
            if (this.frontier.priority_queue.size() == 0){
                throw new Error("Empty Frontier");
            }
            Node new_node = frontier.pop();
            //System.out.println("Popped new node from frontier and added it to explored. This is the node you are traversing");
            //new_node.print_board();
            explored.add(new_node);
            //System.out.println("This is your explored set");
            //explored.print();
            //System.out.println("Depth of Current Traversing Node: " + Integer.toString(new_node.depth));
            //new_node.print_board();
            if (new_node.is_goal()){
                //new java.util.Scanner(System.in).nextLine();
                solution = new_node;
                solution_found = true;
                break;
            }
            
            ArrayList<Node> valid_nodes =  new ArrayList<Node>();
            valid_nodes.add(new_node.move_left());
            valid_nodes.add(new_node.move_right());
            valid_nodes.add(new_node.move_up());
            valid_nodes.add(new_node.move_down());
             
            for (int i=0; i<valid_nodes.size(); i++){
                if (valid_nodes.get(i) != null){
                    num_nodes_gen++;
                    Node valid_node = valid_nodes.get(i);
                    //System.out.println(">>>Found this Valid Node. What will I do?");
                    //valid_node.print_board();
                    if (this.frontier.has_node(valid_node) == -1 && !this.explored.has_node(valid_node)){
                        //System.out.println("Adding to Frontier since it is not in the frontier or explored");
                        //frontier.print();
                        this.frontier.add(valid_node);
                    }
                    else if(frontier.has_node(valid_node) > -1 ){
                        int idx_frontier_node = frontier.has_node(valid_node);
                        if (valid_node.get_cost() < frontier.priority_queue.get(idx_frontier_node).get_cost()){
                            //System.out.println("Replacing Node in frontier");
                            this.frontier.replace_node(valid_node, idx_frontier_node);
                        }
                    }
                }
            }
            //System.out.println("This is your frontier set after you have completed a depth");
        }
        return solution;
    }
}
