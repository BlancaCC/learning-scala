
object Sudoku  extends App{  //extends App {
     
  // val mutable, var inmutable
  
  var _tam: Int = 3; // small squares tam
  var TAM: Int = _tam * _tam;
  val puzzle: Array[ Array[Int ]] = Array.ofDim[Int] (TAM,TAM) // keep in mind yout have clear (tam tam)

  println(s"The puzzle is going to be a ${TAM}" ); 

  fill();
  printSudoku();

  randomMix( 6); 
  println("Changed sudoku")

  printSudoku();


  def fill() : Unit =  {

    /*
     tam = 2
     1 2 | 3 4
     3 4 | 1 2
     - - - - -
     2 3 | 4 1
     4 1 | 2 3

     tam = 3 
     
     |1 2 3 |4 5 6 |7 8 9 |
     |4 5 6 |7 8 9 |1 2 3 |
     |7 8 9 |1 2 3 |4 5 6 |
     - - - - - - - - - - - - 
     |2 3 4 |5 6 7 |8 9 1 |
     |5 6 7 |8 9 1 |2 3 4 |
     |8 9 1 |2 3 4 |5 6 7 |
     - - - - - - - - - - - - 
     |3 4 5 |6 7 8 |9 1 2 |
     |6 7 8 |9 1 2 |3 4 5 |
     |9 1 2 |3 4 5 |6 7 8 |
     - - - - - - - - - - - - 

     */
    for( i <- 0 until _tam) {
      for ( j <- 0 until _tam ) {
        for( k <- 0 until TAM ) {

          puzzle(i*_tam + j)(k) = (i + _tam*j+ k)% TAM+1;

        }    

      }
      
    }

  }

  def printSudoku() : Unit =  {
    for( i <- 0 until _tam) {
      for ( j <- 0 until _tam ) {
        for( k <- 0 until TAM ) {
          if(((k)  % _tam) == 0)
            print("|"); 
          print(s"${puzzle(i*_tam + j)(k)} ");
          
        }
        println("|" ); 

      }
      println("- "*TAM  + "- "*(_tam)); 
    }

  }


  def exchangeRow (i : Int, j : Int ): Unit =  {
    if( i != j && (i-j).abs < _tam && i < TAM && j < TAM) {

      val aux: Array[Int ] = puzzle (i);

      puzzle(i) = puzzle(j);
      puzzle(j) = aux; 

    }
  }

  def exchangeRowBlock (i : Int, j : Int ) : Unit =  {
    if( i != j && (i-j).abs < _tam && i < _tam && j < _tam) {

      var aux: Array[Int ] = puzzle (i);

      for( k <- 0 until _tam) {
       
        aux = puzzle(i*_tam + k);
        puzzle(i*_tam + k) = puzzle(j*_tam + k);
        puzzle(j*_tam + k) = aux;
       }
    }
  }


  def exchangeColumn (i : Int, j : Int ) : Unit =  {
    if( i != j && (i-j).abs < _tam && i < TAM && j < TAM) {

      var aux: Int = 0;

      for( k <- 0 until TAM) {
       aux  = puzzle (k)(i);

        puzzle(k)(i) = puzzle(k)(j);
        puzzle(k)(j) = aux;
      }
    }
  }

  def exchangeColumnBlock (i : Int, j : Int ) : Unit =  {
    if( i != j && (i-j).abs < _tam && i < _tam && j < _tam) {

      var aux: Int = 0;
      for ( w <- 0 until _tam) {
        for( k <- 0 until TAM) {
          aux  = puzzle (k)( i*3 + w);

          puzzle(k)( i*3 + w ) = puzzle(k)(j*3 + w);
          puzzle(k)( j*3 + w ) = aux;
        }
      }
    }
  }

  def randomMix( i: Int ) : Unit = {
    val r = scala.util.Random

    for ( j <- 0 until i) {

      exchangeRow( r.nextInt( TAM), r.nextInt( TAM) ) ;
      exchangeColumn( r.nextInt( TAM),  r.nextInt( TAM )); 
      exchangeRowBlock( r.nextInt( _tam) ,  r.nextInt( _tam)); 
      exchangeColumnBlock( r.nextInt( _tam), r.nextInt( _tam)); 
    }
  }

}
