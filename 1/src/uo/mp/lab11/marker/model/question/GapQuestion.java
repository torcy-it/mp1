package uo.mp.lab11.marker.model.question;

public class GapQuestion extends Question {

	private String rightAnswer;

	/**
	 * 
	 * @param weight
	 * @param rightAnswer
	 * @throws IllegalArgumentException if
	 * 			* weight <= 0
	 * 			* rightAnswer is null or blank
	 */
	public GapQuestion(int number, double weight, String rightAnswer) {
		super(number, weight);
		this.rightAnswer = rightAnswer;
	}

	/**
	 * 
	 * @param answer
	 * @return
	 * @throws IllegalArgumentException if answer is null or blank
	 */
	@Override
	public double mark(String answer) {

		return rightAnswer.equals( answer )
				? getWeight()
				: 0.0;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}
}
