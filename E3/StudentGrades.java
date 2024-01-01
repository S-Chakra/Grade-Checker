import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

//The StudentGrades class calculates the total score by taking the given scores, multiplying them by their respective weights, and adding them together.
public class StudentGrades {
    /**
     * These are fields that contain the students name, gnumber, grades for each category,
     * and the weights for each.
     */
    private double participation, midterm, finalExam;
    private Collection<Double> labs, exercises, projects;
    private List<Double> readings;
    private double participationWeight, readingsWeight, labsWeight;
    private double exercisesWeight, projectsWeight, midtermWeight, finalExamWeight;
    private String studentName, gNumber;

    /**
     * @param name name of student
     * @param gNumber gnumber of student
     * @param weights the weights of each category, in order as shown in 
     * setWeights(double[] weights).
     * 
     * Provides info needed to set variables name, gnumber, and the weights for the categories,
     * then creates ArrayLists for readings, labs, exercises, and projects.
     */
    public StudentGrades(String name, String gNumber, double[] weights) {
        setStudentName(name);
        setGNumber(gNumber);
        setWeights(weights);

        readings = new ArrayList<Double>();
        labs = new ArrayList<Double>();
        exercises = new ArrayList<Double>();
        projects = new ArrayList<Double>();
    }

    /**
     * @param participation
     * Sets the participation field.
     */
    public void setParticipation(double participation){
        this.participation = participation;
    }

    /**
     * @return
     * Returns the participation field.
     */
    public double getParticipation(){
        return participation;
    }

    /**
     * @param midterm
     * Sets the midterm field.
     */
    public void setMidterm(double midterm){
        this.midterm = midterm;
    }

    /**
     * @return
     * Returns the midterm field.
     */
    public double getMidterm(){
        return midterm;
    }

    /**
     * @param finalExam
     * Sets the finalExam field.
     */
    public void setFinalExam(double finalExam){
        this.finalExam = finalExam;
    }

    /**
     * @return
     * Returns the finalExam field.
     */
    public double getFinalExam(){
        return finalExam;
    }

    /**
     * @param studentName
     * Sets the studentName field.
     */
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    /**
     * @return
     * Returns the studentName field.
     */
    public String getStudentName(){
        return studentName;
    }

    /**
     * @param gNumber
     * Sets the gNumber field.
     */
    public void setGNumber(String gNumber){
        this.gNumber = gNumber;
    }

    /**
     * @return
     * Returns the gNumber field.
     */
    public String getGNumber(){
        return gNumber;
    }

    /**
     * @param d
     * Adds a reading grade to the readings ArrayList.
     */
    public void addReading(double d){
        readings.add(d);
    }

    /**
     * @param d
     * Adds a lab score to the labs ArrayList.
     */
    public void addLab(double d){
        labs.add(d);
    }

    /**
     * @param d
     * Adds an exercise score to the exercises ArrayList.
     */
    public void addExercise(double d){
        exercises.add(d);
    }

    /**
     * @param d
     * Adds a project score to the projects Arraylist.
     */
    public void addProject(double d){
        projects.add(d);
    }

    /**
     * @param weights
     * sets the weights of each category to their respctive field given the weights array
     */
    public void setWeights(double[] weights){
        participationWeight = weights[0];
        readingsWeight = weights[1];
        labsWeight = weights[2];
        exercisesWeight = weights[3];
        projectsWeight = weights[4];
        midtermWeight = weights[5];
        finalExamWeight = weights[6];
    }

    /**
     * @return returns the average reading score
     * Calculates the average of the reading score, unweighted.  Excludes the lowerst 15
     * scores. If there are only 15 scores or less, return 100.
     */
    public double unweightedReadingsScore(){
        double average = 0.0;

        if (readings.size() < 16) return 100;

        Collections.sort(readings);
        
        for(int i = 15; i < readings.size(); i++){
            average += readings.get(i);
        }

        average /= (readings.size() - 15);

        return average;
    }

    /**
     * @return returns the average lab score
     * Calculates the average lab score, unweighted.  If there are no scores, return 100.
     */
    public double unweightedLabsScore(){
        double average = 0.0;

        if (labs.size() == 0 || labs == null) return 100;
        
        for(double lab : labs){
            average += lab;
        }

        average /= labs.size();

        return average;
    }

    /**
     * @return returns the average exercise score
     * Calculates the average exercise score.  If there are no scores, return 0.
     */
    public double unweightedExercisesScore(){
        double average = 0.0;

        if (exercises.size() == 0 || exercises == null) return 100;
        
        for(double exercise : exercises){
            average += exercise;
        }

        average /= exercises.size();

        return average;
    }

    /**
     * @return returns the average project score
     * Calculates the average project score.  If there are no scores, return 0.
     */
    public double unweightedProjectsScore(){
        double average = 0.0;

        if (projects.size() == 0 || projects == null) return 100;
        
        for(double project : projects){
            average += project;
        }

        average /= projects.size();

        return average;
    }

    /**
     * @return returns true or false
     * Determines if the final replaces the midterm during final grade calculation.
     */
    public boolean finalReplacesMidterm(){
        if(finalExam > midterm) return true;

        return false;
    }

    /**
     * @return returns true or false
     * Determines of the grade for the final exam is high enough to pass the class.
     */
    public boolean finalIsPassing(){
        if(finalExam >= 60.0) return true;
        
        return false;
    }

    /**
     * @return returns total grade score
     * Calculates the total score by taking each catecory field, multiplying it with
     * their respective weights, and adding them together.  If the final replaces the
     * midterm, multiply it by both the midtermWeight and the finalExamWeight, and
     * don't use midterm grade.  
     */
    public double totalScore(){
        double total = 0.0;

        total += participation * participationWeight;
        total += unweightedReadingsScore() * readingsWeight;
        total += unweightedLabsScore() * labsWeight;
        total += unweightedExercisesScore() * exercisesWeight;
        total += unweightedProjectsScore() * projectsWeight;

        if(finalReplacesMidterm()) total += finalExam * midtermWeight;
        
        else total += midterm * midtermWeight;

        total += finalExam * finalExamWeight;

        return total;
    }

    /**
     * @return returns letter grade
     * Determines the letter grade of student based on the final score.  If the final
     * isn't high enough to pass, return F regardless of final score.
     */
    public String letterGrade(){
        if (!finalIsPassing()) return "F";

        if(totalScore() >= 98) return "A+";
        else if(totalScore() >= 92 && totalScore() < 98) return "A";
        else if(totalScore() >= 90 && totalScore() < 92) return "A-";
        else if(totalScore() >= 88 && totalScore() < 90) return "B+";
        else if(totalScore() >= 82 && totalScore() < 88) return "B";
        else if(totalScore() >= 80 && totalScore() < 82) return "B-";
        else if(totalScore() >= 78 && totalScore() < 80) return "C-";
        else if(totalScore() >= 72 && totalScore() < 78) return "C";
        else if(totalScore() >= 70 && totalScore() < 72) return "C+";
        else if(totalScore() >= 60 && totalScore() < 70) return "D";
        else return "F";
    }

    /**
     * @return returns a String of the students scores
     * Gets the students name, gNumber, scores of each category, final grade score, and
     * final letter grade, and lists them out in a String.
     */
    public String toString(){
		String rv = "Name: "+getStudentName()+"\n";
		rv += "G#: "+getGNumber()+"\n";
		rv += "Participation: "+getParticipation()+"\n";
		rv += "Readings: "+unweightedReadingsScore()+", "+readings+"\n";
		rv += "Labs: "+unweightedLabsScore()+", "+labs+"\n";
		rv += "Exercises: "+unweightedExercisesScore()+", "+exercises+"\n";
		rv += "Projects: "+unweightedProjectsScore()+", "+projects+"\n";
		rv += "Midterm: "+getMidterm()+"\n";
		rv += "Final Exam: "+getFinalExam()+"\n";
		rv += totalScore()+", "+letterGrade()+"\n";
		return rv;
	}
}
