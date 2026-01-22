package com.example.week2native.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week2native.domain.Task
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2native.viewmodel.TaskViewModel
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme



@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text("Todo List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Lista LazyColumnilla
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.tasks, key = { it.id }) { task ->
                Card(  // Taskin ulkonäölle
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // Tyylittelyä listalle
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Checkboxille ja teksteille
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)) {
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(task.title, style = MaterialTheme.typography.titleMedium)
                                Text(task.description, style = MaterialTheme.typography.bodyMedium)
                                Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        // Delete buttonin tyylittelyä
                        Button(
                            onClick = { viewModel.removeTask(task.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Delete", color = MaterialTheme.colorScheme.onError)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Uudet tehtävät tulee täältä
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Enter task") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Enter description") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Enter due date (YY-MM-DD)") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.addTask(
                        Task(
                            id = viewModel.tasks.size + 1,
                            title = title,
                            description = description,
                            dueDate = dueDate,
                            priority = viewModel.tasks.size + 1,
                            done = false
                        )
                    )
                    title = ""
                    description = ""
                    dueDate = ""
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Task")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filterit ja sortit
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.sortByDueDate() },
                modifier = Modifier.weight(1f)
            ) { Text("Sort by date") }

            Button(
                onClick = { viewModel.filterByDone(true) },
                modifier = Modifier.weight(1f)
            ) { Text("Done") }

            Button(
                onClick = { viewModel.filterByDone(false) },
                modifier = Modifier.weight(1f)
            ) { Text("Not done") }

            Button(
                onClick = { viewModel.showAll() },
                modifier = Modifier.weight(1f)
            ) { Text("Show all") }
        }

    }
}
