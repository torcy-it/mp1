package uo.mp.lab11.marker.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;
import uo.mp.lab11.marker.model.StudentMark;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.parser.ExamParser;
import uo.mp.lab11.marker.parser.QuestionParser;
import uo.mp.lab11.marker.service.comparator.ByMarkAscendingComparator;
import uo.mp.lab11.marker.service.comparator.ByMarkDescendingComparator;
import uo.mp.lab11.marker.service.serializers.StudentMarkSerializer;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.file.FileUtil;
import uo.mp.util.file.ZipFileUtil;


public class ExamMarker {
	private static final int MIN_FILENAME_LENGHT = 5;
	/*
	 * file questions.txt is read and parsed into questions list
	 */
	private List<Question> questions = new ArrayList<>();
	/*
	 * file answers.gz is read and parsed into answers list
	 */
	private List<StudentExam> answers = new ArrayList<>();
	/*
	 * Student marks are computed and stored into marks list
	 */
	private List<StudentMark> marks = new ArrayList<>();


	/**
	 * 
	 * @param examModelFile
	 * @throws ExamMarkerException 
	 * @throws IllegalArgumentException if examModelFile is null or blank
	 * @throws FileNotFoundException if examModelFile cannot be found
	 */
	public void loadQuestions(String questionsFilename) throws ExamMarkerException {
		ArgumentChecks.isTrue(questionsFilename !=null );
		ArgumentChecks.isTrue(!questionsFilename.isBlank() );
		
		checkFileNname(questionsFilename);
		List<String> lines = readQuestionLines(questionsFilename);

		List<Question> questions = new QuestionParser().parse( lines );
		addQuestions( questions );
	}
	
	private void checkFileNname(String questionsFilename) throws ExamMarkerException{
		if(questionsFilename.length() < MIN_FILENAME_LENGHT) {
			throw new ExamMarkerException("Error en lectura de fichero");
		}
	}
	
	
	private List<String> readQuestionLines(String questionFilename) throws ExamMarkerException {
			
		try {
				return new FileUtil().readLines(questionFilename);
				
			}catch(FileNotFoundException e) {
				throw new ExamMarkerException("Error en la lectura de fichero");
			}
			
		
	}
	
	
	/*
	 * TODO : FAKE IMPLEMENTATION
	 */
	private void addQuestions(List<Question> quest) {
		this.questions = new ArrayList<>(quest);
		
	}

	/**
	 * 
	 * @param answersFilename
	 * @throws FileNotFoundException if answersFile cannot be found
	 * @throws IllegalArgumentException if answersFilename is null or blank
	 * @throws ExamMarkerException when there are more than one exam for the same student 
	 */
	public void loadAnswers(String answersFilename) throws ExamMarkerException {

		List<String> lines = readAnswerLines(answersFilename);
		
		List<StudentExam> exams = new ExamParser().parse( lines );
		addExams( exams );
	}

	
	private List<String> readAnswerLines(String answersFilename) throws ExamMarkerException {
		
		try {
			return new ZipFileUtil().readLines(answersFilename);
			}catch(FileNotFoundException e) {
				throw new ExamMarkerException("Error en lectura del fichero" + answersFilename);
			}
	}

	/*
	 * TODO: FAKE IMPLEMENTATION
	 */
	private void addExams(List<StudentExam> exams) {
		this.answers = new ArrayList<>(exams);
	}


	/**
	 * 
	 * @return the list of marks ordered by student id in ascending order
	 */
	public List<StudentMark> getMarksByStudent() {

		Collections.sort(marks, new ByMarkAscendingComparator());
		
		return this.marks;	
	}

	/**
	 * 
	 * @return the list of marks ordered by grade in ascending order
	 * 			For the same grade, by ascending student id
	 */
	public List<StudentMark> getMarksByMark() {

		Collections.sort(marks, new ByMarkDescendingComparator());
		
		return this.marks;	
		
	}

	/**
	 * calculates the mark for each exam. 
	 * Generates StudentMark instances
	 */
	public void mark() {
		marks.clear();
		for(StudentExam exam : answers) {
			double total = exam.mark(questions);
			marks.add(new StudentMark(exam.getId(), total));
		}
	}

	/**
	 * 
	 * @param resultsFilename
	 * @throws IllegalArgumentException if resultsFilename is null or blank
	 */
	public void saveResults(String resultsFilename) {
		StudentMarkSerializer serializer = new StudentMarkSerializer();
		List<String> lines = serializer.serialize(marks);
		new FileUtil().writeLines(resultsFilename, lines);
	}


	public List<StudentExam> getAnswers() {
		// TODO FAKE IMPLEMENTATION
		return answers ;
	}

	public List<Question> getQuestions() {
		// TODO FAKE IMPLEMENTATION
		return questions;
	}


}
