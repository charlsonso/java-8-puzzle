import java.util.Scanner;
class AStarBasicTest{
    public static void main(String[] args){
        System.out.print("Enter Board: ");
        Scanner sc = new Scanner(System.in);
        String board = sc.nextLine();
        int[] board_value = new int[9];
        int count = 0;
        for (int i=0; i<board.length(); i++){
            if (i%2 == 0){
                char value = board.charAt(i);
                board_value[count] = Character.getNumericValue(value);
                count++;
            }
        }
        System.out.print("Enter Mode: ");
        int mode = sc.nextInt();
        Node initial = new Node(board_value, null, 0, mode);
        AStar search = new AStar(initial);
        Node solution = search.search_for_solution();
        System.out.println("Found Solution");
        solution.print_board();
    }
}
