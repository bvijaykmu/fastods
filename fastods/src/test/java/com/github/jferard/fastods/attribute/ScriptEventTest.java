package com.github.jferard.fastods.attribute;

import org.junit.Assert;
import org.junit.Test;

public class ScriptEventTest {
    @Test
    public void testOnSelect() {
        Assert.assertEquals("dom:select", ScriptEvent.ON_SELECT.getValue());
        Assert.assertEquals("dom:select", ScriptEvent.ON_SELECT.toString());
    }
}