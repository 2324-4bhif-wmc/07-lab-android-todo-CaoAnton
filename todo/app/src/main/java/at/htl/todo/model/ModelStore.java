package at.htl.todo.model;

import android.util.Log;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;
import at.htl.todo.util.store.Store;

@Singleton
public class ModelStore extends Store<Model>  {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos) {
        apply(model -> model.todos = todos);
    }

    public void selectTab(int tabIndex) {
        apply(model -> model.uiState.selectedTab = tabIndex);
    }

    public void updateCompletedToDo(Long id, boolean completed) {
        apply(model -> {
            Arrays.stream(model.todos)
                    .filter(todo -> todo.id.equals(id))
                    .findFirst()
                    .ifPresent(todo -> {
                        todo.completed = completed;
                        Log.i("ModelStore", "Todo with ID " + id + " updated to completed: " + completed);
                    });
        });
    }
}