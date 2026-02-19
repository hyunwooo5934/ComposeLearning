package com.example.composeex.feature.home.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import com.example.domain.model.weekBoxOfficeModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    selectedItem: weekBoxOfficeModel,
    sheetState: SheetState,
    onDataExchange: (item: weekBoxOfficeModel?) -> Unit,
    onClose: (state: SheetState) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDataExchange(null) },
        sheetState = sheetState
    ) {
        BottomSheetContent(
            item = selectedItem,
            onClose = { onClose(sheetState) }
        )
    }
}