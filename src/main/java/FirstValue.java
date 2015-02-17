public class FirstValue implements Value {

	private String string;

	public FirstValue() {
	}

	public FirstValue(String value) {
		this.string = value;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
