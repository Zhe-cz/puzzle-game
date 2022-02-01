package lab.game.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import lab.game.model.Score;

/**
 * game FileUtil
 */
public class FileUtil {
	/**
	 * The read file information is encapsulated in the Score class
	 */
	public static ArrayList<Score> inUtil() {
		ArrayList<Score> list = new ArrayList<Score>();
		File file = new File("src/main/resources/lab/game/record/rank.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			while ((text = reader.readLine()) != null) {
				String[] data = text.split(" ");
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				int scores = Integer.parseInt(data[2]);
				Score score =new Score(id,name,scores);
				list.add(score);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return  list;
	}
	/**
	 * sort ArrayList
	 * @param list
	 */
	public static void sort(ArrayList<Score> list){

		Collections.sort(list, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getScores() - (s1.getScores());
			}
		});

	}

	/**
	 * Writes information from the collection
	 */
	public static void outUtil(ArrayList<Score> list) {
		File outFile = new File("src/main/resources/lab/game/record/rank.txt");
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(outFile, false));
			for (Score s : list) {
				out.append(s.getScore_id() + " " + s.getPlayer() + " " + s.getScores() + "\n");
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (Exception e) {
			}
		}
	}




}
