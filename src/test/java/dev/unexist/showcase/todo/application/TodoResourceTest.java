/**
 * @package Showcase-Microstream-Helidon
 *
 * @file Stupid integration test
 * @copyright 2020-present Christoph Kappel <christoph@unexist.dev>
 * @version $Id$
 *
 * This program can be distributed under the terms of the Apache License v2.0.
 * See the file LICENSE for details.
 **/

package dev.unexist.showcase.todo.application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Disabled
public class TodoResourceTest {

    @Test
    public void testTodoEndpoint() {
        given()
          .when().get("/todo")
          .then()
             .statusCode(204);
    }
}