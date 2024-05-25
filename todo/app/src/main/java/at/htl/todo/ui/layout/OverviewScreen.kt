package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo
import at.htl.todo.model.TodoService

@Composable
fun OverviewScreen(model: Model, store: ModelStore) {
    val todos = model.todos;

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        items(todos.size) { index ->
            TodoRow(todo  = todos[index], store = store)
            HorizontalDivider()
        }
    }

}

@Composable
fun TodoRow(todo: Todo, store: ModelStore) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Clip,
            )
        }
        Column{
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {
                    // update the completed
                    store.updateCompletedToDo(todo.id, !todo.completed)
                }
            )
        }
    }
}