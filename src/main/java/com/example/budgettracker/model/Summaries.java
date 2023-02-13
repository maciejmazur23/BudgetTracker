package com.example.budgettracker.model;

import java.util.List;

//@Getter
//@RequiredArgsConstructor
public record Summaries(List<Summary> monthSummaries,
                        List<Summary> yearSummaries) {
}
