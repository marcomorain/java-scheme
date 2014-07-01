package com.marcomorain.scheme;

import java.io.StringReader;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {
  
    @Test
    public void testParseToken() throws Exception {
        App instance = new App(new StringReader("(1 22 378 abcd)"));
        assertThat(instance.parseToken(), equalTo(App.Token.LEFT_PAREN));
        assertThat(instance.parseToken(), equalTo(App.Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(App.Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(App.Token.NUMBER));
        assertThat(instance.parseToken(), equalTo(App.Token.IDENTIFIER));
        assertThat(instance.parseToken(), equalTo(App.Token.RIGHT_PAREN));
    }

    
}
