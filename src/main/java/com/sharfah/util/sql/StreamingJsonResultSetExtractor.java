package com.sharfah.util.sql;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Streams a ResultSet as JSON.
 * @author fahd
 */
public class StreamingJsonResultSetExtractor implements ResultSetExtractor<Void> {

  private final OutputStream os;

  /**
   * @param os the OutputStream to stream the JSON to
   */
  public StreamingJsonResultSetExtractor(final OutputStream os) {
    this.os = os;
  }

  @Override
  public Void extractData(final ResultSet rs) {
    final var objectMapper = new ObjectMapper();
    try (var jg = objectMapper.getFactory().createGenerator(os, JsonEncoding.UTF8)) {
      writeResultSetToJson(rs, jg);
      jg.flush();
    } catch (IOException | SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  private static void writeResultSetToJson(final ResultSet rs, final JsonGenerator jg)
      throws SQLException, IOException {
    final var rsmd = rs.getMetaData();
    final var columnCount = rsmd.getColumnCount();
    jg.writeStartArray();
    while (rs.next()) {
      jg.writeStartObject();
      for (var i = 1; i <= columnCount; i++) {
        jg.writeObjectField(rsmd.getColumnName(i), rs.getObject(i));
      }
      jg.writeEndObject();
    }
    jg.writeEndArray();
  }
}
