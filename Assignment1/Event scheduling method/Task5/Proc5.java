package Task5;
// This is an abstract class which all classes that are used for defining real 
// process types inherit. The puropse is to make sure that they all define the 
// method treatSignal, which is needed in the main program.


public abstract class Proc5 extends Global5{
	public abstract void TreatSignal(Signal5 x);
}

