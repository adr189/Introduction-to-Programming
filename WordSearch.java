import java.util.Scanner;
import java.util.Arrays;
/**
 *
 * Partida de Sopa-de-Letras jogada numa quadrícula, onde estão escondidas
 * várias palavras.
 *
 * @author Alexandre Rodrigues fc54472
 *
 */
public class WordSearch {

  /**
   * Verifica se a palavra está esondida em alguma linha ou coluna da quadrícula,
   * na ordem normal ou invertida
   * @param board quadrícula
   * @param word  palavra
   * @return true se word está escondida em board
   * @requires {@code board.isMatrix() && word!=null}
   */
   public static boolean isHidden(char[][] board,String word){//ola
     boolean bol = false;
     int linha = board.length;//4
     int coluna = board[0].length;//5
     int tes=0;
     //horizontal para frente
     for (int i = 0;i < linha ;i++) {
       //ler tudo de cima para baixo
       for (int e =0;e <= coluna - word.length() ;e++) {
         //para ler as vezes que a palavra caber
         tes=0;
         for(int a=0;a<word.length();a++){
           if (board[i][e+a] == word.charAt(a)) {
             tes++;
             if(tes == word.length()){
               bol=true;
             }
           }
         }
       }
     }
     //horizontal para tras
     for (int i = 0;i < linha ;i++) {
       //ler tudo de cima para baixo
       for (int e = coluna - word.length();e >= 0; e = e - 1) {
         tes=0;
         for(int a=word.length();a > 0;a=a-1){
           for (int wordcheck=0;wordcheck<word.length();wordcheck++) {
             if (board[i][e+a -1] == word.charAt(wordcheck)) {
               tes++;
               if(tes == word.length()){
                 bol=true;
               }
             }
           }
         }
       }
     }
     for (int e =0;e < coluna ;e++){
       //ler tudo de cima para baixo
       for (int i = 0;i <= linha - word.length() ;i++){
         //para ler as vezes que a palavra caber
         tes=0;
         for(int a=0;a<word.length();a++){
           if (board[i+a][e] == word.charAt(a)) {
             tes++;
             if(tes == word.length()){
               bol=true;
             }
           }
         }
       }
     }
     for (int e =0;e < coluna ;e++){
       //ler tudo de cima para baixo
       for (int i = linha - word.length();i >= 0;i = i - 1 ){
         //para ler as vezes que a palavra caber
         tes=0;
         for(int a=word.length();a > 0;a=a-1){
           for (int wordcheck=0;wordcheck<word.length();wordcheck++) {
             if (board[i+a-1][e] == word.charAt(wordcheck)) {
               tes++;
               if(tes == word.length()){
                 bol=true;
               }
             }
           }
         }
       }
     }

     return bol;
   }

  /**
   * Verifica se para todo 0<=i<=hiddenWords.length, a palavra hiddenWords[i]
   * está escondida em board
   * @param board quadrícula
   * @param hiddenWords palavras escondidas
   * @return true se a palavra hiddenWords[i] está escondida em board
   * @requires {@code board.isMatrix() && hiddenWords!=null}
   */
   public static boolean isValidGame(char[][] board, String[] hiddenWords){
 		boolean bal=false;
 		int a =0;
 		int i=0;
 		for (i = 0;i < hiddenWords.length;i++) {
 			if (isHidden( board , hiddenWords[i] ) == true) {
 				a++;
 			}
 		}
 		if (a==i) {
 			bal=true;
 		}
 		return bal;
 	}

  /**
   * Verifica se move tem quatro elementos e são efetivamente linhas e colunas da
   * quadrícula de dimensão rows x columns e definem posições contíguas todas na
   * mesma linha ou na mesma coluna
   * @param move jogada
   * @param rows linhas
   * @param columns colunas
   * @return true se move tem 4 elementos e são de dimensão rows x columns
   * @requires {@code move!=null && rows>0 && colums>0}
   */
   public static boolean isValidMove(int[] move,int rows,int columns){
 		while(move.length != 0 && rows >0 && columns>0){
 			if (move.length == 4 ) {
 				for (int i = 0;i < move.length ;i++ ) {
 					if(move[0] <= rows && move[1] <= columns && (move[0] == move[2]||move[1]==move[3])){
 						return true;
 					}
 					else {
 						return false;
 					}
 				}
 			}
 			else {
 				return false;
 			}
 		}
 		return false;
 	}

  /**
   * Lẽ uma jogada através do canal dado.
   * @param sc scanner
   * @param rows linhas
   * @param columns colunas
   * @return moves a jogada
   * @requires {@code sc!=null && rows>0 && colums>0}
   */
   public static int[] readMove(Scanner sc,int rows ,int columns){
 		int moves[] = new int[4] ;
 		System.out.print("Give me your move:");
 		for (int i=0;i < 4;i++) {
 			moves[i]=sc.nextInt();
 		}
 		while(isValidMove(moves,rows,columns) == false){
 			System.out.println("Your move is invalid");
 			System.out.print("Give me your move:");
 			for (int i=0;i < 4;i++) {
 				moves[i]=sc.nextInt();
 			}
 		}
 		System.out.print("Give me your move:");
 		return moves;
 	}

  /**
   * Verifica se alguma das palavras escondidas foi encontrada na jogada move
   * @param board quadrícula
   * @param move jogada
   * @param hiddenWords palavras escondidas
   * @return a palavra escondida
   * @requires {@code isValidGame(char[][] board, String[] hiddenWords)
   * && isValidMove(move, board.length, board[0].length)}
   */
   public static String findWord(char[][] board,int[] move,String[] hiddenWords){
 		String word = null;
 		if (isValidGame(board,hiddenWords) == true) {
 			if (isValidMove(move,board.length,board[0].length) == true) {
 				for (int i =0;i< hiddenWords.length ;i++ ) {
 					String word2= hiddenWords[i];
 					int counter1 = 0;
 					int counter2 = 0;
 					int counter3 = 0;
 					int counter4 = 0;
 					//horizontal
 					if (move[0]==move[2]) {
 						if (move[1] < move[3]) {
 							for (int a=0;a < word2.length();a++) {
 								if(board[move[0]][move[1]+a] == word2.charAt(a)){
 									counter1++;
 								}
 							}
 							if (counter1 == word2.length()) {
 								word = word2;
 							}
 						}
 						if (move[3] < move[1]) {
 							for (int a=0;a < word2.length();a++) {
 								if(board[move[0]][move[1]-a] == word2.charAt(a)){
 									counter2++;
 								}
 							}
 							if (counter2 == word2.length()) {
 								word = word2;
 							}
 						}
 					}
 					//vertical
 					if (move[1]==move[3]) {
 						if (move[0] < move[2]) {
 							for (int a=0;a < word2.length();a++) {
 								if(board[move[0]+a][move[1]] == word2.charAt(a)){
 									counter3++;
 								}
 							}
 							if (counter3 == word2.length()) {
 								word = word2;
 							}
 						}
 						if (move[2] < move[0]) {
 							for (int a=0;a < word2.length();a++) {
 								if(board[move[2]-a][move[1]] == word2.charAt(a)){
 									counter3++;
 								}
 							}
 							if (counter3 == word2.length()) {
 								word = word2;
 							}
 						}
 					}

 				}
 			}
 		}
 		return word;
 	}

  /**
   * Imprime a quadrícula e o número de palavras que ele tem escondidas
   * @param board quadrícula
   * @param hiddenWords palavras escondidas
   * @requires {@code board.isMatrix() && hiddenWords!=null}
   */
  public static void printPuzzle(char[][] board, String[] hiddenWords) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("Hidden words:" + hiddenWords.length);
  }

  public static void main(String[] args) {
    PuzzleReader puzzle = new PuzzleReader(args[0]);
    Scanner sc = new Scanner(System.in);

    if (!isValidGame(puzzle.getPuzzle(),puzzle.getHiddenWords())) {
			System.out.println("The game is not valid.");
		} else {
      printPuzzle(puzzle.getPuzzle(),puzzle.getHiddenWords());
      if (!isValidMove(readMove(sc,puzzle.getPuzzle().length,puzzle.getPuzzle()[0].length),puzzle.getPuzzle().length,puzzle.getPuzzle()[0].length)) {
        System.out.print("Your move is invalid.");
      } else {
        readMove(sc,puzzle.getPuzzle().length,puzzle.getPuzzle()[0].length);
      }
    }
  }
}
