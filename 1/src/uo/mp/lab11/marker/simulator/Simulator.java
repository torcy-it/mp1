package uo.mp.lab11.marker.simulator;

import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;
import uo.mp.lab11.marker.model.StudentMark;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.service.ExamMarker;
import uo.mp.lab11.marker.service.ExamMarkerException;
import uo.mp.lab11.util.log.Logger;
import uo.mp.util.console.Console;

public class Simulator {

	private static final String EXAM_MODEL_FILE = "questions.txt";
	private static final String ANSWERS_FILE = "answers.gz";
	private static final String RESULTS_FILE = "marks.txt";

	public void start() {
		try {
			simulateScenario();
		} catch (RuntimeException e) {
			handleSystemError(e);
			return;
		} catch (Exception e) {
			handleUserException(e.getMessage());
		}
	}

	private void handleUserException(String message) {
		Console.println("ERROR IRRECUPERABILE: " + message + "Ejecute de nuevo");
		
	}
	private void handleSystemError(RuntimeException e) {
		Console.println("ERROR IRRECUPERABILE: " + e.getMessage() );
		Logger.log(e);
		
	}

	/**
	 * There is no user interface for this small program. This method simulates an
	 * example scenario of use.
	 * 
	 * @throws ExamMarkerException
	 */
	private void simulateScenario() throws ExamMarkerException {
		ExamMarker ex = new ExamMarker();
		ex.loadQuestions(EXAM_MODEL_FILE);
		showQuestions(ex.getQuestions());
		ex.loadAnswers(ANSWERS_FILE);
		showExams(ex.getAnswers());

		ex.mark();
		
		//tarea: ordinazione, salvare i resultati sul file e test del parser Questions
		
		showMarks(ex.getMarksByMark(), " by ascending mark");
		showMarks(ex.getMarksByStudent(), " by ascending student id");
		ex.saveResults(RESULTS_FILE);
	}

	private void showQuestions(List<Question> questions) {
		System.out.println("---------------------------------------------");
		System.out.println("List of questions");
		for (Question q : questions) {
			System.out.println(q.toString());
		}
	}

	private void showExams(List<StudentExam> exams) {
		System.out.println("---------------------------------------------");
		System.out.println("List of exams");
		for (StudentExam se : exams) {
			System.out.println(se.toString());
		}
	}

	private void showMarks(List<StudentMark> marks, String string) {
		System.out.println("---------------------------------------------");
		System.out.println("List of marks" + string);
		for (StudentMark mark : marks) {
			System.out.println(mark.toString());
		}
	}

}
