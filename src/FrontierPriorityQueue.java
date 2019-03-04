import java.util.ArrayList;

class FrontierPriorityQueue{
    public ArrayList<Node> priority_queue;

    FrontierPriorityQueue(){
        priority_queue = new ArrayList<Node>();
    }
    public void add(Node new_node){
        int idx = priority_queue.size();
        for (int i = 0; i<priority_queue.size(); i++){
            Node compare_node = priority_queue.get(i);
            if (compare_node.get_cost() > new_node.get_cost()){
                idx = i;
                break;
            }
        }
        if (idx == priority_queue.size()){
            priority_queue.add(new_node);
        }
        else{
            priority_queue.add(idx, new_node);
        }
    }

    //If node is in frontier priority queue, return the index in the priority queue that it is located in
    public int has_node(Node new_node){
        for (int i =0; i<priority_queue.size(); i++){
            Node compare_node = priority_queue.get(i);
            if (compare_node.is_node_similar(new_node)){
                return i;
            }
        }
        return -1;
    }

    //Remove Node at idx
    //Add new node
    public void replace_node(Node new_node, int idx){
        priority_queue.remove(idx);
        add(new_node);
    }

    public Node pop(){
        return priority_queue.remove(0);
    }

    public void print(){
        System.out.println(">>> Printing Frontier:");
        for (int i = 0; i<priority_queue.size(); i++){
            priority_queue.get(i).print_board();
            System.out.println(">>> Cost:" + Integer.toString(priority_queue.get(i).cost));
        }
    }
}
