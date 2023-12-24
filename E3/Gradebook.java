import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Gradebook implements Comparator<StudentGrades>{
	private Collection<StudentGrades> grades;

	public int compare(StudentGrades left, StudentGrades right){
		return (int)(left.totalScore()-right.totalScore());
	}

	//provided
	public String toString(){
		String rv = "Grades: [ ";
		for(StudentGrades sg : grades){
			rv += "("+sg.getStudentName()+": "+sg.letterGrade()+"), ";
		}
		rv += "]\n";
		rv += "Max: "+max()+", Median: "+median()+", Average: "+average()+", Min: "+min();
		return rv;
	}

	//method that simply adds the new ArrayList created of StudentGrades to the grades field
	public Gradebook(){
		grades = new ArrayList<StudentGrades>();
	}

	/**
	 * @param sg takes in parameter StudentGrades and adds it to the grades field
	 */
	public void addGrade(StudentGrades sg){
		grades.add(sg);
	}

	/**
	 * @return returns the average
	 * for this method a variable of type double was created
	 * a for loop is used to iterate through the length of the grades
	 * for every occurrence the total score of that grades at that index is added to theAverage
	 * that is repeated for every iteration of the for loop
	 * then the theAverage which should contain all the values summed up is divided by the size of the grades and returned
	 * casting is also used tp cast the collection grades into an arrayList
	 */
	public double average(){
		int average = 0;

		for(StudentGrades sg : grades){
			average += sg.totalScore();
		}

		return average / grades.size();
	}

	/**
	 * @return return sg
	 * an object of StudentGrades with value theVal is created and assigned the value of the first grade
	 * casting is also used to convert the collection into an arraylist
	 * a for loop is iterating through the size of the grades and if the grade at that index is less than the initial value which is in theVal
	 * then theVal gets reassigned with the new value which is smaller
	 * the .get(i) gets the value at every index since we're iterating through the for loop with i
	 * 
	 */
	public StudentGrades min(){
		StudentGrades sg = ((ArrayList<StudentGrades>)grades).get(0);

		for (int i = 0; i < grades.size(); i++){
			if (((ArrayList<StudentGrades>)grades).get(i).totalScore() < sg.totalScore())
				sg = ((ArrayList<StudentGrades>)grades).get(i);
			
		}

		return sg;
	}

	/**
	 * @return this method implements the same logic as getting the minimum value
	 * the only difference is we're checking if the grade at that index is greater than the initial value which is assigned to theVal
	 * if it is theVal is replaced with the new value which is now the max and that process is repeated until the whole list is iterated through
	 * after the whole list is iterated through, we would have the max value and return it
	 */
	public StudentGrades max(){
		StudentGrades sg = ((ArrayList<StudentGrades>)grades).get(0);

		for (int i = 0; i < grades.size(); i++){
			if (((ArrayList<StudentGrades>)grades).get(i).totalScore() > sg.totalScore())
				sg = ((ArrayList<StudentGrades>)grades).get(i);
			
		}

		return sg;
	}

	/**
	 * @return returns the median
	 * this method creates a variable of type int and assigns it with the size of the grade divided by 2
	 * since we're using / and will always be given a list that has an odd length no further verifiction is required
	 * and integer division returns the whole number which is the median
	 * then we cast the grades to turn the collection into an arrayList
	 * the for loop iterated through the size of the grades -1 and creates a new variable that keeps track of the index
	 * the nested for loop iterated through the next index from i in the size of g which is the grades
	 * then the smallest index is updatted to j which is i + 1
	 * then we create an object of studentgrades and set is equal to the value at i using get()
	 * then we set or copy the value at indexSmallest to i and update the value at indexSmallest with the value stored in temp
	 * finally the middle number is returned 
	 */
	public StudentGrades median(){
		int mid = grades.size() / 2;
		ArrayList<StudentGrades> g = (ArrayList<StudentGrades>)grades;

		for (int i = 0; i < g.size() - 1; ++i) {

			int indexSmallest = i;
			for (int j = i + 1; j < g.size(); ++j) {
		 
			   if (g.get(j).totalScore() < g.get(indexSmallest).totalScore()) {
				  indexSmallest = j;
			   	}
			}
		 
			StudentGrades temp = g.get(i);
			g.set(i, g.get(indexSmallest));
			g.set(indexSmallest, temp);
		}
		
		return g.get(mid);
	}
}