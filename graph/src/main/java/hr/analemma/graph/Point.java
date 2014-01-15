
package hr.analemma.graph;

import java.math.BigDecimal;

@Deprecated
public class Point {

	private Integer simno;
	private BigDecimal recoveryTime;

	public Point(String simno, String recoveryTime) {

		super();

		this.simno = Integer.parseInt(simno.trim());
		try{
		this.recoveryTime = new BigDecimal(recoveryTime.trim());
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}

	public Integer getSimno() {

		return simno;
	}

	public void setSimno(Integer simno) {

		this.simno = simno;
	}

	public BigDecimal getRecoveryTime() {

		return recoveryTime;
	}

	public void setRecoveryTime(BigDecimal recoveryTime) {

		this.recoveryTime = recoveryTime;
	}

}
