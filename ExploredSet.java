import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

class ExploredSet{
    public Set<Node> explored_set;
    
    ExploredSet(){
        explored_set = new HashSet<Node>();
    }

    public void add(Node new_node){
        if (!has_node(new_node)){
            explored_set.add(new_node);
        }
    }

    public boolean has_node(Node new_node){
        Iterator<Node> it = explored_set.iterator();
        boolean exists = false;
        while(it.hasNext()){
            Node compare_node = it.next();
            if(compare_node.is_node_similar(new_node)){
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void print(){
        Iterator<Node> it = explored_set.iterator();
        System.out.println("Printing Explored Set");
        while(it.hasNext()){
            Node node = it.next();
            node.print_board();
        }
    }
}
