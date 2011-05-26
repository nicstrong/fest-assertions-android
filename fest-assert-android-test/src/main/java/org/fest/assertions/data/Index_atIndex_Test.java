/*
 * Created on Oct 30, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.data;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.test.ExpectedException.none;

import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Index#atIndex(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class Index_atIndex_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_throw_error_if_value_is_negative() {
    thrown.expectIllegalArgumentException("The value of the index should not be negative");
    Index.atIndex(-1);
  }

  @Test public void should_create_new_Index() {
    Index index = Index.atIndex(8);
    assertEquals(8, index.value);
  }
}