public class Oferta {

	Autonomo autonomo;
	private int id;
	private String desc;
	private Date fi;
	private Date ff;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getFi() {
		return this.fi;
	}

	public void setFi(Date fi) {
		this.fi = fi;
	}

	public Date getFf() {
		return this.ff;
	}

	public void setFf(Date ff) {
		this.ff = ff;
	}

	public Oferta() {
		throw new UnsupportedOperationException();
	}
}
