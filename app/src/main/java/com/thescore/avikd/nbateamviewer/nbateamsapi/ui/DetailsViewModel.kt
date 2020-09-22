

package com.thescore.avikd.nbateamviewer.nbateamsapi.ui

import androidx.lifecycle.ViewModel
import com.thescore.avikd.nbateamviewer.databinding.ActivityMainBinding
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.ListViewRepository
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team
import javax.inject.Inject

/**
 * The ViewModel used in [DetailsViewFragment].
 */
class DetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var listItemData: Team
}
