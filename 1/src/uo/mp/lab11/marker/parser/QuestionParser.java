package uo.mp.lab11.marker.parser;

import java.util.LinkedList;
import java.util.List;

import uo.mp.lab11.exception.InvalidLineFormatException;
import uo.mp.lab11.marker.model.question.ChoiceQuestion;
import uo.mp.lab11.marker.model.question.GapQuestion;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.model.question.ValueQuestion;
import uo.mp.lab11.util.log.Logger;
import uo.mp.util.check.ArgumentChecks;

public class QuestionParser {
	private int lineNumber = 1;

	/**
	 * 
	 * @param lines
	 * @return
	 * @throws IllegalArgumentException if lines is null
	 */
	/*public List<Question> parse(List<String> lines) {
		List<Question> res = new LinkedList<>();
		
		
		for (String line : lines) {
			lineNumber++;
			try {
				checkIsBlanck(line);
				checkNumberOfField (line);
				checkNumber ( line );
				checkQuestion(line);
				
				
				Question question = parseLine(line);

				res.add(question);
			} catch (InvalidLineFormatException e) {
				Logger.log(e.getMessage());
			}

		}
		return res;
	}
	*/	
	public List<Question> parse(List<String> lines) {
		

	ArgumentChecks.isTrue(lines !=null, "illegal null test");
  
  	List<Question> res = new LinkedList<Question>();
  
  		for(String line: lines){
  			try{
  				checkIsBlanck(line);
  				Question q = parseLine(line);
  				res.add(q);
  			}catch(InvalidLineFormatException e){
  				Logger.log(e.getMessage());
  			}
  			lineNumber++;
  		}
  		return res;
  }

	private void checkQuestion(String line) throws InvalidLineFormatException {
		String[] tokens = line.split("\t");
		String type = tokens[0];

		if (!type.equals("choice") && !type.equals("value") && !type.equals("gap"))
			throw new InvalidLineFormatException(lineNumber, "Invalid question");

	}

	private void checkNumberOfField(String line) throws InvalidLineFormatException {
		String[] tokens = line.split("\t");

		if (tokens.length != 3)
			throw new InvalidLineFormatException(lineNumber, "numero de campo invalido");

	}

	private void checkNumber(String line) throws InvalidLineFormatException {

		if (toDouble(line) <= 0)
			throw new InvalidLineFormatException(lineNumber, "Number not  valid");

	}

	private void checkIsBlanck(String line) throws InvalidLineFormatException {
		if (line.isBlank()) {
			throw new InvalidLineFormatException(lineNumber, "Linea en blanco");
		}

	}

	private Question parseLine(String line) throws InvalidLineFormatException {

		String[] tokens = line.split("\t");
		String type = tokens[0];

		if (type.equals("choice")) {
			return parseChoice(tokens);
		} else if (type.equals("gap")) {
			return parseGap(tokens);

		} else if (type.equals("value")) {
			return parseValue(tokens);

		} else {

			throw new InvalidLineFormatException(lineNumber, "tipo desconoscido");
		}
	}

	private Question parseChoice(String[] tokens) throws InvalidLineFormatException {

		double point = toDouble(tokens[1]);

		String rightAnswer = tokens[2];

		return new ChoiceQuestion(lineNumber, point, rightAnswer);

	}

	private Question parseGap(String[] tokens) throws InvalidLineFormatException {

		double point = toDouble(tokens[1]);

		String rightAnswer = tokens[2];

		return new GapQuestion(lineNumber, point, rightAnswer);

	}

	private Question parseValue(String[] tokens) throws InvalidLineFormatException {

		double point = toDouble(tokens[1]);

		checkNumber(tokens[2]);

		double rightAnswer = toDouble(tokens[2]);

		return new ValueQuestion(lineNumber, point, rightAnswer);

	}

	private double toDouble(String string) throws InvalidLineFormatException {

		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException e) {
			throw new InvalidLineFormatException(lineNumber, "formato de numero erroneo");
		}
	}

}
