package com.example.budgettracker.model;

import java.util.List;

public record Summaries(List<Summary> monthSummaries,
                        List<Summary> yearSummaries) {
}
