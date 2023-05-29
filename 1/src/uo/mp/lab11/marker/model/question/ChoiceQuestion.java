package uo.mp.lab11.marker.model.question;

public class ChoiceQuestion extends Question {

	private String rightAnswer;

	/**
	 * 
	 * @param weight
	 * @param rightAnswer
	 * @throws IllegalArgumentException if
	 * 			* weight <= 0
	 * 			* rightAnswer is null or blank
	 */
	public ChoiceQuestion(int number, double weight, String rightAnswer) {
		super( number, weight );
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
		return getRightAnswer().equals( answer ) 
				? getWeight() 
				: getWeight() * -0.2;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

}
