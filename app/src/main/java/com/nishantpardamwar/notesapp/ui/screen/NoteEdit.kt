package com.nishantpardamwar.notesapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nishantpardamwar.notesapp.ui.UIActionNoteCreate
import com.nishantpardamwar.notesapp.ui.UIActionNoteEdit
import com.nishantpardamwar.notesapp.ui.model.UINote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEdit(note: UINote, uiAction: (UIActionNoteEdit) -> Unit) {
    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content ?: "") }
    val createBtnEnabled by remember { derivedStateOf { title.isNotEmpty() } }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(modifier = Modifier.padding(start = 16.dp), text = "Edit Note")
        }, navigationIcon = {
            Icon(modifier = Modifier
                .clickable(onClick = {
                    uiAction(UIActionNoteEdit.Back)
                })
                .padding(8.dp), imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        })
    }, content = { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = title,
                onValueChange = { title = it },
                label = {
                    Text("Enter Note Title")
                })
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = content,
                onValueChange = { content = it },
                label = {
                    Text("Enter Note Content (Optional)")
                })
            Spacer(Modifier.height(16.dp))
            Button(
                enabled = createBtnEnabled, onClick = { uiAction(UIActionNoteEdit.NoteEdit(note, title, content)) }) {
                Text("Update")
            }
        }
    })
}