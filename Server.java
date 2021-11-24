package data;

public class Server {
	
	String fName;
	String lName;
	String sID;
	double tipTotal;
	
	public Server(String fName, String lName, String sID) {
		this.fName = fName;
		this.lName = lName;
		this.sID = sID;
		tipTotal = 0;
	}
	
	public double addTip(double tipAmount) {
		this.tipTotal = this.tipTotal + tipAmount;
		return this.tipTotal;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getsID() {
		return sID;
	}

	public double getTipTotal() {
		return tipTotal;
	}
	
}
