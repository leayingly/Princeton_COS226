import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
	
	final private int[][] blocks;
	final private int n;
	private int man = 0, hamming = 0;
	
    
    public Board(int[][] blocks) {
    	// construct a board from an n-by-n array of blocks
    	// (where blocks[i][j] = block in row i, column j)
//    	n = blocks.length;
    	this.blocks = blocks;
    	this.n = this.blocks.length;
    	
       	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (this.blocks[i][j] != i*n +j + 1 && this.blocks[i][j] != 0) {
    				hamming++;
    				int i_target = (this.blocks[i][j] - 1) / n;
    				int j_target = (this.blocks[i][j] - 1) % n;
    				man += Math.abs(i - i_target) + Math.abs(j - j_target);
    			}
    		}
    	}
    }
                                           
    public int dimension() {
    	// board dimension n
    	return n;
    }
    
    public int hamming() {
    	// number of blocks out of place
    	return hamming;
    }
    
    public int manhattan() {
    	// sum of Manhattan distances between blocks and goal
    	return man;
    }
    
    public boolean isGoal() {
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (this.blocks[i][j] != i*n +j + 1 && this.blocks[i][j] != 0) {
    				return false;
    			}
    		}
    	}

    	return true;
    }
    
    public Board twin() {
    	// a board that is obtained by exchanging any pair of blocks
    	int i1 = -1, i2 = -1,j1 = -1,j2 = -1;
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (this.blocks[i][j] != 0) {
    				if (i1 == -1) {
    					i1 = i; 
    					j1 = j;
    					continue;
    				}
    				if (i2 == -1) {
    					i2 = i;
    					j2 = j;
    					break;
    				} 
    			}
    		}
    	}

    	
    	int [][] copy = new int[n][];
    	for(int i = 0; i < n; i++)
    		copy[i] = this.blocks[i].clone();
    	
    	int temp = copy[i1][j1];
    	copy[i1][j1] = copy[i2][j2];
    	copy[i2][j2] = temp;
    	return new Board(copy);
    }
    
    public boolean equals(Object y) {
    	// does this board equal y?
    	if (y == null) {
    		return false;
    	} else {
    		try {
    			int[][] temp = ((Board) y).blocks;
    			return Arrays.deepEquals(temp, this.blocks);
    		} catch (Exception e) {
    			return false;
    		}
    	}
    }
    
    private Board swatZero(int[][] original, int i0, int j0, int ix, int jx) {
    	int [][] copy = new int[n][];
    	for(int i = 0; i < n; i++)
    		copy[i] = original[i].clone();
    	
    	int temp = copy[i0][j0];
    	copy[i0][j0] = copy[ix][jx];
    	copy[ix][jx] = temp;
    	return new Board(copy);
    }
    
    public Iterable<Board> neighbors() {
    	// all neighboring boards
    	List<Board> neighbor = new ArrayList<Board>();
    	int i0= -1, j0 = -1;
    	
    	outer_loop:
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (this.blocks[i][j] == 0) {
    				i0 = i;
    				j0 = j; 
    				break outer_loop;
    			}
    		}
    	}
    	
    	if (i0 - 1 >= 0) neighbor.add(swatZero(this.blocks, i0, j0, i0 - 1, j0));
    	if (i0 + 1 < n)  neighbor.add(swatZero(this.blocks, i0, j0, i0 + 1, j0));
    	if (j0 - 1 >= 0) neighbor.add(swatZero(this.blocks, i0, j0, i0, j0 - 1));
    	if (j0 + 1 < n)  neighbor.add(swatZero(this.blocks, i0, j0, i0, j0 + 1));

    	return neighbor;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", this.blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    
    public static void main(String[] args) {
    	// unit tests (not graded)
    }
}

