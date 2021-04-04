package jp.empressia.logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * ロガーを簡単に取得する手段を提供します。
 * import static jp.empressia.logging.ELogger.logger;
 * 
 * @author すふぃあ
 */
public final class ELogger {

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger log(Object caller) {
		return ELogger.getLogger(caller.getClass().getName());
	}

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger log(Class<Object> caller) {
		return ELogger.getLogger(caller.getName());
	}

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger log(Package caller) {
		return ELogger.getLogger(caller.getName());
	}

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger logger(Object caller) {
		return ELogger.getLogger(caller.getClass().getName());
	}

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger logger(Class<Object> caller) {
		return ELogger.getLogger(caller.getName());
	}

	/**
	 * ロガーを取得します。
	 * @param caller
	 * @return ロガー
	 */
	public static final Logger logger(Package caller) {
		return ELogger.getLogger(caller.getName());
	}

	/**
	 * ロガーを取得します。存在しない場合は作成されます。
	 * @param loggerName
	 * @return 名前に対応したロガー
	 */
	public static final Logger getLogger(String loggerName) {
		Logger l = LogManager.getLogManager().getLogger(loggerName);
		if(l == null) {
			try {
				Lock.acquire();
				try {
					l = Logger.getLogger(loggerName);
					for(CreatedListener handler : Handlers) {
						handler.created(l);
					}
				} finally {
					Lock.release();
				}
			} catch(InterruptedException ex) {
				// 普通起きない。
			}
		}
		return l;
	}

	private static final Semaphore Lock = new Semaphore(1);
	/** Logger生成の監視者一覧。 */
	private static final List<CreatedListener> Handlers = Collections.synchronizedList(new ArrayList<CreatedListener>());
	/** Logger生成の監視対象に追加します。 */
	public static final void addListener(CreatedListener handler) {
		Handlers.add(handler);
	}
	/** Logger生成の監視対象から外します。 */
	public static final void removeListener(CreatedListener handler) {
		Handlers.remove(handler);
	}
	/** ELoggerによるLogger生成を監視するListener */
	@FunctionalInterface
	public static interface CreatedListener {
		/** Loggerが生成された */
		void created(Logger logger);
	}

}