package com.sharfah.util.sql;

import static com.sharfah.util.hamcrest.IsEqualJSON.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;

import org.junit.Test;

public class StreamingJsonResultSetExtractorTest {

  @Test
  public void testExtractData() throws SQLException {
    final var bos = new ByteArrayOutputStream();
    final var extractor = new StreamingJsonResultSetExtractor(bos);
    final var rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {
          { "Alice", 20 },
          { "Bob", 35 },
          { "Charles", 50 }
      });
    extractor.extractData(rs);
    final var json = new String(bos.toByteArray());
    assertThat(json, equalToJSON("["
        + "{\"name\":\"Alice\",\"age\":20},"
        + "{\"name\":\"Bob\",\"age\":35},"
        + "{\"name\":\"Charles\",\"age\":50}"
        + "]"));
  }

  @Test
  public void testEmptyResultSet() throws SQLException {
    final var bos = new ByteArrayOutputStream();
    final var extractor = new StreamingJsonResultSetExtractor(bos);
    final var rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {});
    extractor.extractData(rs);
    final var json = new String(bos.toByteArray());
    assertThat(json, equalToJSON("[]"));
  }
}
