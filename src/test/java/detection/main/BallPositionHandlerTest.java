package detection.main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import detection.main.BallPosition;
import detection.main.BallPositionHandler;
import detection.main.ConfiguratorValues;

public class BallPositionHandlerTest {

	@Before
	public void init() {

		ConfiguratorValues.setOffsetX(0);
		ConfiguratorValues.setOffsetY(0);
	}

	@Test
	public void valueFromStringShouldBeTransferedCorrectlyIntoInteger() {

		String string = "100";

		BallPositionHandler positionHandler = new BallPositionHandler();

		assertThat(positionHandler.getValueFromString(string), is(100));
	}

	@Test
	public void stringShouldBeTransferedCorrectlyIntoBallPosition() {

		int xCoordinate = 100;
		int yCoordinate = 200;
		String timepoint = anyTimepoint();

		BallPositionHandler positionHandler = new BallPositionHandler();

		BallPosition ballPosition = positionHandler
				.createBallPositionFrom(timepoint + "|" + xCoordinate + "|" + yCoordinate);

		assertThat(ballPosition.getXCoordinate(), is(100));
		assertThat(ballPosition.getYCoordinate(), is(200));

		assertThat(String.valueOf(ballPosition.getTimepoint().getTime()), is("154166463822"));
	}

	@Test
	public void ballPositionValuesGetSetCorrectlyWithOffset() {

		ConfiguratorValues.setOffsetX(50);
		ConfiguratorValues.setOffsetY(100);

		BallPosition ballPosition = new BallPosition(50, 100, new Date());

		assertThat(ballPosition.getXCoordinate(), is(50));
		assertThat(ballPosition.getYCoordinate(), is(100));

	}

	@Test
	public void ballPositionValuesGetNormedCorrectly() {

		BallPosition ballPosition = new BallPosition();

		ballPosition.setXCoordinate(100);
		ballPosition.setYCoordinate(500);
		ballPosition.setTimepoint(1541664638);

		ConfiguratorValues.setGameFieldSize(1000, 1000);

		assertThat(ballPosition.normedXPosition(), is(0.1));
		assertThat(ballPosition.normedYPosition(), is(0.5));
	}

	private String anyTimepoint() {
		return "1541664638.22";
	}
}