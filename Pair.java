public class Pair{
    public int row;
    public int col;

    public Pair(int value){
        //Enter 1-D Value
        this.row = value / 3;
        this.col = value % 3;
    }

    public Pair(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int get_row(){
        return row;
    }

    public int get_col(){
        return col;
    }

    public int get_idx(){
        return row * 3 + col;
    }
}
