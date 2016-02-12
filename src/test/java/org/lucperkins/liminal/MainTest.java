package org.lucperkins.liminal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testEnv() {
        Env env;
        env = Env.fromArgs(new String[]{"DEV"});
        assertEquals(env, Env.DEV);
        env = Env.fromArgs(new String[]{"PROD"});
        assertEquals(env, Env.PROD);
        env = Env.fromArgs(new String[]{"dev"});
        assertEquals(env, Env.DEV);
        env = Env.fromArgs(new String[]{"prod"});
        assertEquals(env, Env.PROD);
        env = Env.fromArgs(new String[]{"quasi", "Kilmeister", "Braunschweig"});
        assertEquals(env, Env.DEV);
    }
}
