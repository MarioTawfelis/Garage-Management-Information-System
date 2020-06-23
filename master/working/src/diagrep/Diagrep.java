/**
 *
 * @author Nexus
 */
package diagrep;

import java.sql.Time;
import java.util.Date;

public class Diagrep
{
	private final int id;
	private final String type;
	private final String date;
	private final String duration;
	private final String vehReg;
	private final String vehManufacturer;
	private final int vehMileage;
	private final int custID;
	private final String custFirstName;
	private final String custLastName;
	private final int mechID;
	private final String mechDuration;
	
	public Diagrep (int id, String type, String date, String duration, String vehReg, String vehManufacturer, int vehMileage, int custID, String custFirstName, String custLastName, int mechID, String mechDuration)
	{
		this.id = id;
		this.type = type;
		this.date = date;
		this.duration = duration;
		this.vehReg = vehReg;
		this.vehManufacturer = vehManufacturer;
		this.vehMileage = vehMileage;
		this.custID = custID;
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.mechID = mechID;
		this.mechDuration = mechDuration;
	}
	
	public int getID()
	{
		return this.id;
	}
	public String getType()
	{
		return this.type;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getDuration()
	{
		return this.duration;
	}
	public String getVehReg()
	{
		return this.vehReg;
	}
	public String getVehManufacturer()
	{
		return this.vehManufacturer;
	}
	public int getVehMileage()
	{
		return this.vehMileage;
	}
	public int getCustID()
	{
		return this.custID;
	}
	public String getCustFirstName()
	{
		return this.custFirstName;
	}
	public String getCustLastName()
	{
		return this.custLastName;
	}
	public int getMechID()
	{
		return this.mechID;
	}
	public String getMechDuration()
	{
		return this.mechDuration;
	}
}
