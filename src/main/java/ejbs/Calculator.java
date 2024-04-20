package ejbs;

//import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculator {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	int number1;
	int number2;
	String operation;
	
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public int Calculation() {
		if(operation.equals("+"))
		{
			return (number1+number2);
		}
		else if(operation.equals("-"))
		{
			return (number1-number2);
		}
		else if(operation.equals("*"))
		{
			return (number1*number2);
		}
		else if(operation.equals("/"))
		{
			if(number2 == 0)
			{
				 throw new IllegalArgumentException("Can't divide by 0");
			}
			else
			{
				return (number1/number2);
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid operation: " + operation);
		}
	}
}
