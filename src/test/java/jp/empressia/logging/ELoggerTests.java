package jp.empressia.logging;

import static jp.empressia.logging.ELogger.logger;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.logging.Level;

import org.junit.jupiter.api.Test;

/**
 * ELoggerのテスト。
 */
public class ELoggerTests {

	@Test
	public void クラスパス上のログ設定を読み込んだLoggerが得られる() {
		Level level = logger(this).getLevel();
		assertAll(
			() -> assertThat("メッセージのレベルが確認できる。", level, is(Level.FINEST))
		);
	}

}
