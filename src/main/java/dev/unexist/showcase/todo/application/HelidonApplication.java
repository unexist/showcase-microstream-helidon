package dev.unexist.showcase.todo.application;

import dev.unexist.showcase.todo.adapter.TodoResource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("/")
public class HelidonApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();

        set.add(TodoResource.class);

        return Collections.unmodifiableSet(set);
    }
}
