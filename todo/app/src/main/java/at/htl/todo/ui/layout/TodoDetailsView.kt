package at.htl.todo.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.Todo

@Composable
fun Todos(model: Model) {
    val todos = model.todos

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        items(todos.size) { index ->
            DetailTodoRow(todo = todos[index])
            if (index < todos.size - 1) {
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }
}

@Composable
fun DetailTodoRow(todo: Todo) {
    val textDecoration: TextDecoration? = if (todo.completed) TextDecoration.LineThrough else null
    val color: Color = if (todo.completed) Color.LightGray else MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        DetailTextRow(label = "Id", value = todo.id.toString(), color = color, textDecoration = textDecoration)
        DetailTextRow(label = "Title", value = todo.title, color = color, textDecoration = textDecoration)
        CompletedRow(completed = todo.completed, color = color)
        DetailTextRow(label = "UserId", value = todo.userId.toString(), color = color, textDecoration = textDecoration)
    }
}

@Composable
fun CompletedRow(completed: Boolean, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Completed: ",
            color = color,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Checkbox(
            checked = completed,
            onCheckedChange = null,
            enabled = false,
        )
    }
}

@Composable
fun DetailTextRow(label: String, value: String, color: Color, textDecoration: TextDecoration?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: ",
            color = color,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = value,
            color = color,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            textDecoration = textDecoration
        )
    }
}
