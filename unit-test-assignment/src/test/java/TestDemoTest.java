import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
class TestDemoTest {
private TestDemo testDemo;
	@BeforeEach
	void setUp() throws Exception {
		 testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(
			int a, int b, int expected, Boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			}
		else
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
		        .isInstanceOf(IllegalArgumentException.class);
	}
	
	private static Stream<Arguments> argumentsForAddPositive() {
	return	Stream.of(
			arguments(2, 4, 6, false),
			arguments(16, -4, 12, true),
			arguments(4, -4, 0, true),
			arguments(8, -2, 6, true),
			arguments(-9, 4, -5, true),
			arguments(-2, -2, -4, true),
			arguments(-100, 100, 0, true),
			arguments(25, 80, 105, false)
			);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
	
	
}
