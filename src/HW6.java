import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class HW6 {
	//need to figure out what is wrong with the find function and why it cant find stuff
	public static void main(String[] args){

		HashDictionary<Integer, Integer> testInserts = new HashDictionary<>();

		HashDictionary<String, Integer> checkFind = new HashDictionary<>(10);
		HashDictionary<Integer, Double> anotherType = new HashDictionary<>(10);


		System.out.println("This will be demonstrating the process of a linear probe in the insert method and its method of collision resolution");
		testInserts.insert(4, 3);
		testInserts.insert(14, 5);
		testInserts.insert(24, 3);

		testInserts.clear();
		System.out.println();
		System.out.println("This will be demonstrating the process of a quadratic probe in the insert method and its method of collision resolution");

		testInserts.quadraticInsert(4, 3);
		testInserts.quadraticInsert(14, 5);
		testInserts.quadraticInsert(24, 4);

		testInserts.clear();
		System.out.println();
		System.out.println("This will be demonstrating the process of a pseudo random probe in the insert method and its method of collision resolution");

		System.out.println("This is the random array for reference: "+ testInserts.printOutRandomArray());

		testInserts.psuedoRandomInsert(4, 3);
		testInserts.psuedoRandomInsert(14, 5);
		testInserts.psuedoRandomInsert(24, 4);


		System.out.println("-------------------------------------------------------------------");


		System.out.println("This will be testing the insert to make sure it can not add duplicates and to show the dictionary can take other types");
		anotherType.insert(3, 3.2);
		anotherType.insert(4, 4.0);
		System.out.println("\n\n ---------------------------------------------------");

		checkFind.insert("meme", 5);
		checkFind.insert("that", 2);

		System.out.println("This will be testing the find function, the cases where a key is present, and where it is not");
		System.out.println("Is the string meme a key and if so, what is its value: " + checkFind.find("meme") );
		System.out.println("Is the string bob in the dictionary and if so, what is its value: " + checkFind.find("bob"));

		System.out.println("\n\n---------------------------------------------------");
		System.out.println("This is testing the remove function, the first case is removing something present in the dictionary, the second is where it is not.");
		checkFind.remove("meme");
		checkFind.remove("meme");

		System.out.println("\n\n--------------------------------------------------");
		System.out.println("This is for the cases of removeAny");
		System.out.println("The first case is that there is an element to remove, and the second is the dictionary is empty");
		System.out.print("The first case:");
		System.out.println(" What was removed: " + checkFind.removeAny());
		System.out.println("The second case: " + checkFind.removeAny());




		//
		//
		//lines 16-94 are for runtime and step analysis of the different probing methods
		System.out.println("The output between the double cross lines is for reproduction of the empirical analysis");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		try {
			int sizeMan = 300;
			int stepClocker = 0;
			Path path = Paths.get("empiricalAnalysis.csv");
			Path stepPath = Paths.get("stepAnalysis.csv");
			BufferedWriter stepWriter = Files.newBufferedWriter(stepPath);
			BufferedWriter bufferedWriter = Files.newBufferedWriter(path);

			HashDictionary<Integer, Integer> TestifCompile = new HashDictionary<>(sizeMan);
			for (int i = 0; i < TestifCompile.size(); i++) {
				Random random = new Random();
				int insertValue = random.nextInt();
				Long before = System.nanoTime();
				stepClocker = TestifCompile.insert(insertValue, 4);
				Long after = System.nanoTime();

				Long differenceInTime = after - before;
				String writeToCSV = differenceInTime.toString() + ",";
				bufferedWriter.write(writeToCSV, 0, writeToCSV.length());
				String writeToSteps = stepClocker + ",";
				stepWriter.write(writeToSteps, 0, writeToSteps.length());
				stepWriter.flush();
			}
			stepWriter.write("\n", 0, 1);
			bufferedWriter.write("\n", 0, 1);

			//in worst case of constantly inserting with the same key quadratic probe will not even fill the hash table
			HashDictionary<Integer, String> testingQuadraticProbing = new HashDictionary<>(6011);
			for (int i = 0; i < testingQuadraticProbing.size(); i++) {


				Random random = new Random();

				int insertValue = random.nextInt();
				Long before = System.nanoTime();
				stepClocker = testingQuadraticProbing.quadraticInsert(insertValue, "Value");
				Long after = System.nanoTime();
				Long differenceInTime = after - before;
				if (differenceInTime <= 70000) {
				String writeToCSV = differenceInTime.toString() + ",";
				bufferedWriter.write(writeToCSV, 0, writeToCSV.length());
				bufferedWriter.flush();
					String writeToSteps = stepClocker + ",";
					stepWriter.write(writeToSteps, 0, writeToSteps.length());
					stepWriter.flush();
				}
			}
			stepWriter.write("\n", 0, 1);
			bufferedWriter.write("\n", 0, 1);

			HashDictionary<Integer, Double> testingPsudoRandom = new HashDictionary<>(sizeMan);
			for (int i = 0; i < testingPsudoRandom.size(); i++) {
				Random random = new Random();
				int insertValue = random.nextInt();

				Long before = System.nanoTime();
				stepClocker = testingPsudoRandom.psuedoRandomInsert(insertValue, 3.4);
				Long after = System.nanoTime();

				Long differenceInTime = after - before;
				//attempt to reduce outliers
				if (differenceInTime <= 70000){
				String writeToCSV = differenceInTime.toString() + ",";
				bufferedWriter.write(writeToCSV, 0, writeToCSV.length());
				bufferedWriter.flush();
				String writeToSteps = stepClocker + ",";
				stepWriter.write(writeToSteps, 0, writeToSteps.length());
				stepWriter.flush();
				}
			}
			stepWriter.write("\n", 0, 1);
			bufferedWriter.write("\n", 0, 1);



			for (int i = 0; i < testingPsudoRandom.size(); i++){
				Integer qwer = i + 1;
				String ja = qwer.toString() + ",";
				bufferedWriter.write(ja, 0, ja.length());
				stepWriter.write(ja, 0, ja.length());
				stepWriter.flush();
			}
			bufferedWriter.close();

			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}




	}




}
