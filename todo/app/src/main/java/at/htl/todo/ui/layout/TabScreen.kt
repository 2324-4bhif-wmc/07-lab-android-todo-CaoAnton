package at.htl.todo.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.TodoService
import at.htl.todo.util.store.Store

@Composable
fun TabScreen(model: Model, store: ModelStore?) {
    var uiState = model.uiState
    val tabIndex = uiState.selectedTab
    val tabs = listOf("Overview", "Todos")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = uiState.selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { store?.selectTab(index)},
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> BadgedBox(badge = { Badge { Text("${model.todos.size}") }}) {
                                Icon(Icons.Filled.List, contentDescription = "ToDos")
                            }
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> store?.let { OverviewScreen(model, it) }
            1 -> Todos(model)
        }
    }
}