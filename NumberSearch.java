/**
 *
 * Determina se uma sequência está escondida numa linha e a sequência definida
 * por uma jogada.
 *
 * @author Alexandre Rodrigues fc54472
 *
 */
public class NumberSearch {

	/**
	 * Devolve o número de dígitos de num
	 * @param num número inteiro
	 * @return count número de digitos de num
	 * @requires {@code num > 0}
	 */
	public static int digits (int num) {
		int count = 0;
		while (num > 0) {
			num = num / 10;
			count = count + 1;
		}
		return count;
	}

	/**
	 * Devolve o número como os dígitos na ordem inversa
	 * @param num número inteiro
	 * @return result num invertido
	 * @requires {@code num > 0 && digits(num) <= 9}
	 */
	public static int reverseDigits (int num) {
		int result = 0;
		while (num > 0 && digits(num) <= 9) {
			result = result * 10 + num % 10;
			num = num / 10;
		}
		return result;
	}

	/**
	 * Indica se a sequência de dígitos de num1 é uma subsequência da sequência de
	 * dígitos de num2
	 * @param num1 primeiro número inteiro
	 * @param num2 segundo número inteiro
	 * @return  se é uma subsequência da sequência
	 * @requires {@code num1 > 0 && num2 > 0}
	 */
	public static boolean isSubsequence (int num1, int num2) {
		int size1 = digits(num1);                                       // numero de digitos em num1
		int size2 = digits(num2);                                       // numero de digitos em num2
		int remainder1 = 10;                                            // divisor para o resto com num1 (cortar à esquerda)
		int remainder2 = 10;                                            // divisor para o resto com num2 (cortar à esquerda)
		int divisor1 = 10;                                              // divisor para a divisão inteira com num1 (cortar à direita)
		int divisor2 = 10;                                              // divisor para a divisão inteira com num2 (cortar à direita)

		for (int i = 1; i < size1; i++){                                    // inicializar o remainder1 para apontar para o primeiro digito
			remainder1 *= 10;
		}

		for (int i = 1; i < size2; i++){                                    // inicializar o remainder2 para apontar para o primeiro digito
			remainder2 *= 10;
		}

		for (int i = 2; i < size1; i++){                                    // inicializar o divisor1 para apontar para o primeiro digito
			divisor1 *= 10;
		}

		for (int i = 2; i < size2; i++){                                    // inicializar o divisor2 para apontar para o primeiro digito
			divisor2 *= 10;
		}

		int counter = 0;                                                // counter de quantos digitos correspondentes já encontrámos

		for (int i = 0; i < size2; i++){                                     // percorrer os digitos do num2
			if (num2 % remainder2 / divisor2==num1 % remainder1 / divisor1){     // se houver uma correspondecia entre um digito X de num1 e num2
				remainder1 /=10;                                         // avançamos para verificar o próximo digito do num1
				divisor1 /=10;                                           // -^
				counter++;                                              // incrementamos as correspondencias consecutivas
			}
			else if (counter < size1){                                    // se não encontrarmos uma correspondecia a meio de uma verificação -> seguimos em frente
				counter = 0;                                              // correspondencias passam a ser 0
				remainder1 = 10;                                        // reiniciamos os nossos "apontadores"
				divisor1 = 10;                                          // -^
				for (int j = 1; j < size1; j++){                            // -^
					remainder1 *= 10;                                   // -^
				}                                                       // -^
				for (int j = 2; j < size1; j++){                            // -^
					divisor1 *= 10;                                     // -^
				}                                                       // -^
			}
			if (counter == size1)                                          // Quando temos correspondencia total, então já encontramos a subsequencia
				return true;

			remainder2 /= 10;                                             // andar um digito para a direita no num2
			divisor2 /= 10;                                               // -^
		}

		return false;
	}

	/**
	 * Devolve o número formado pelos dígitos nas posições from até to de num
	 * @param num primeiro número inteiro
	 * @param from segundo número inteiro
	 * @param to terceiro número inteiro
	 * @return se for número formado pelos dígitos nas posições from até to de num
	 * @requires {@code num > 0 && 1 <= from <= to <= digits(num)}
	 */
	public static int subsequence (int num, int from, int to){
		int n_digits = digits(num);                 // número de digitos do num
		int iterationsToStart = n_digits-from;        // quantas multiplicações por 10 é preciso para chegar ao inicio da sequencia
		int iterationsToEnd = n_digits-to;            // quantas multiplicações por 10 é preciso para chegar ao fim da sequencia
		int restBy = 0;                             // o resto corta os digitos do inicio
		int divisionBy = 0;                         // a divisão corta os digitos do fim

		for (int i = 0; i < iterationsToStart; i++){   // for para multiplicar "restBy" por 10
			if (restBy == 0){
				restBy += 10;
			}
			restBy *= 10;
		}

		for (int i = 0; i < iterationsToEnd; i++){     // for para multiplicar "divisionBy" por 10
			if (divisionBy == 0){
				divisionBy += 10;
			} else {
				divisionBy *= 10;
			}
		}

		if (restBy == 0){                              // se esta variável for zero dá erro (e.g. 9%0)
			if (divisionBy == 0)                       // se esta variável for zero dá erro (e.g. 9/0)
				return num;
			return num / divisionBy;
		}
		if (divisionBy == 0)
			return num % restBy;
		return num%restBy / divisionBy;

	}

	/**
	 * Verifica se a linha de números é válida
	 * @param num número inteiro
	 * @param numberDigits número de digitos
	 * @return true se num > 0, digits(num) == numberDigits e é composto por
	 * digitos de 1 a 9
	 * @requires {@code numberDigits > 0}
	 */
	public static boolean isValidRow (int num, int numberDigits) {
		if (num > 0 && digits(num) == numberDigits) {
			while (numberDigits > 0) {
				if (num % 10 != 0) {
					num = num / 10;
					numberDigits = numberDigits - 1;
				} else {
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Verifica se a sequência de números é válida
	 * @param num número inteiro
	 * @param numberDigits número de digitos
	 * @return true se num > 0, digits(num) <= numberDigits e é composto por
	 * digitos de 1 a 9
	 * @requires {@code numberDigits > 0}
	 */
	public static boolean isValidSequence (int num, int numberDigits) {
		if (num > 0 && digits(num) <= numberDigits) {
			while (numberDigits > 0) {
				if (num % 10 != 0) {
					num = num / 10;
					numberDigits = numberDigits - 1;
				} else {
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Verifica se a row e a sequência são ambas válidas
	 * @param numberDigits número de digitos
	 * @param row linha
	 * @param sequence sequência
	 * @requires {@code numberDigits > 0}
	 */
	public static void verifySequence (int numberDigits, int row, int sequence) {
		if (numberDigits == digits(row)) {
			if (isSubsequence (sequence, row) && isValidSequence(sequence, digits(sequence))) {
				System.out.println("The sequence " + sequence + " is hidden in row "+ row +".");
			}
			else if (isSubsequence (reverseDigits(sequence), row) && isValidSequence(sequence, digits(sequence))) {
				System.out.println("The sequence " + sequence + " is hidden in row "+ row +".");
			}
			else if (!isSubsequence (sequence, row) && isValidSequence(sequence, digits(sequence))) {
				System.out.println("The sequence " + sequence + " is not hidden in row "+ row +".");
			}
			else if (!isSubsequence (reverseDigits(sequence), row) && isValidSequence(sequence, digits(sequence))) {
				System.out.println("The sequence " + sequence + " is not hidden in row "+ row +".");
			}
			else if (!isValidSequence(sequence, digits(sequence))) {
				System.out.println("The sequence " + sequence + " is not valid.");
			}
		}
		else {
			if (isValidSequence(sequence, digits(sequence))) {
				System.out.println("The row " + row + " is not valid.");
			}
			else {
				System.out.println("The row " + row + " is not valid. The sequence " + sequence + " is not valid.");
			}
		}
	}

	/**
	 * Verifica se o par from, to define um intervalo de posições válidas da
	 * sequência de dígitos de row
	 * @param row linha
	 * @param from primeira posição do intervalo
	 * @param to última posição do intervalo
	 * @requires {@code row > 0}
	 */
	public static void verifyNum (int row, int from, int to) {
		if (row > 0 && 1 <= from && from <= to && to <= digits(row)) {
			System.out.println("The sequence from position " + from + " to " + to + " in row " + row + " is " + subsequence(row, from, to) + ".");
		} else {
			System.out.println("The range from " + from + " to " + to + " is not valid in row " + row + ".");
		}
	}

	public static void main(String[] args) {
		int a, b, c;
		a = 9;
		b = 198745334;
		c = 533;
		verifySequence(a,b,c);
		b = 198745334;
		c = 335;
		verifySequence(a,b,c);
		b = 198745334;
		c = 5334;
		verifySequence(a,b,c);
		b = 198745334;
		c = 121;
		verifySequence(a,b,c);
		b = 19874533;
		c = 335;
		verifySequence(a,b,c);
		b = 198745334;
		c = 305;
		verifySequence(a,b,c);
		b = 19874533;
		c = 305;
		verifySequence(a,b,c);
		System.out.println();
		int d,e,f;
		d = 198745334;
		e = 6;
		f = 8;
		verifyNum(d,e,f);
		e = 1;
		f = 5;
		verifyNum(d,e,f);
		e = 8;
		f = 6;
		verifyNum(d,e,f);
		e = 6;
		f = 12;
		verifyNum(d,e,f);
	}
}
