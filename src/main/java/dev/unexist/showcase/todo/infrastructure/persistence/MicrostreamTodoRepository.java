package dev.unexist.showcase.todo.infrastructure.persistence;

import dev.unexist.showcase.todo.domain.todo.Todo;
import dev.unexist.showcase.todo.domain.todo.TodoRepository;
import one.microstream.reference.Lazy;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named(value = "microstream")
public class MicrostreamTodoRepository implements TodoRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MicrostreamTodoRepository.class);

    final EmbeddedStorageManager storageManager;

    MicrostreamTodoRepository() {
        DataRoot dataRoot = new DataRoot();

        /* Create storage */
        this.storageManager = EmbeddedStorage.start();

        this.storageManager.setRoot(dataRoot);
        this.storageManager.storeRoot();

        LOGGER.info("Microstream ready");
    }

    @Override
    public boolean add(Todo todo) {
        DataRoot dataRoot = (DataRoot)this.storageManager.root();

        return dataRoot.add(todo);
    }

    @Override
    public boolean update(Todo todo) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<Todo> getAll() {
        DataRoot dataRoot = (DataRoot)this.storageManager.root();

        return Collections.unmodifiableList(dataRoot.list.get());
    }

    @Override
    public Optional<Todo> findById(int id) {
        return Optional.empty();
    }

    private static class DataRoot {
        private final Lazy<List<Todo>> list;

        DataRoot() {
            this.list = Lazy.Reference(new ArrayList<>());
        }

        public boolean add(Todo todo) {
            todo.setId(this.list.get().size() + 1);

            return this.list.get().add(todo);
        }
    }
}
