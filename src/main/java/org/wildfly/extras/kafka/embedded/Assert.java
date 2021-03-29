package org.wildfly.extras.kafka.embedded;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:kabir.khan@jboss.com">Kabir Khan</a>
 */
class Assert {
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new AssertionError(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notNull(Object expression, String message) {
        if (expression == null) {
            throw new AssertionError(message);
        }
    }


    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? (String)messageSupplier.get() : null;
    }

    public static void hasText(String expression, String message) {
        if (expression == null || expression.trim().length() == 0) {
            throw new AssertionError(message);
        }
    }
}
