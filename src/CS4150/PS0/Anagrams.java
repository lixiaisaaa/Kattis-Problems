package PS0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Anagrams {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int n = s.nextInt(), k = s.nextInt();

		HashSet<String> nonAnagramList = new HashSet<String>();
		HashSet<String> anagramList = new HashSet<String>();
		for (int i = 0; i < n; i++) {

			char temp[] = s.next().toCharArray();
			Arrays.sort(temp);

			String anagram = new String(temp);


			if (!anagramList.contains(anagram)) {
				if (!nonAnagramList.contains(anagram)) {
					nonAnagramList.add(anagram);

				} else {
					nonAnagramList.remove(anagram);
					anagramList.add(anagram);
				}
			}
		}

		System.out.println(nonAnagramList.size());
	}

}
