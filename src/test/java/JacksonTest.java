import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mapper = new ObjectMapper();
	}

	@Test
	public void successfulTest() throws Exception {
		mapper.registerSubtypes(FirstValue.class);
		mapper.registerSubtypes(OtherValue.class);

		writeReadValue(new FirstValue("some value"));
		writeReadValue(new OtherValue("some other value"));
	}

	@Test
	public void failingTest() throws Exception {
		mapper.registerSubtypes(FirstValue.class);

		writeReadValue(new FirstValue("some value"));

		mapper.registerSubtypes(OtherValue.class);

		writeReadValue(new OtherValue("some other value"));
	}

	private void writeReadValue(Value value) throws Exception {
		String json = mapper.writeValueAsString(new Holder(value));
		Value readValue = mapper.readValue(json, Holder.class).getValue();
		Assert.assertEquals(value.getString(), readValue.getString());
	}
}
