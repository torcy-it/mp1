package uo.mp.lab11.marker.model.question;

public abstract class Question {

	private int questionNumber;
	private Double weight;

	/**
	 * @param number
	 * @param weight
 	 * @throws IllegalArgumentException if question <= 0
	 * @throws IllegalArgumentException if weight <= 0
	 */
	public Question(int number, double weight) {

		this.questionNumber = number;
		this.weight = weight;
	}

	/**
	 * 
	 * @param answer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public abstract double mark(String answer);

	public double getWeight() {
		return weight;
	}
	
	public int getNumber() {
		return this.questionNumber;
	}

	@Override
	public String toString() {
		return "Question [questionNumber=" + questionNumber + ", weight=" + weight + "]";
	}

	
}
