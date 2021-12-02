use common::read_file;

pub fn execute() -> i32 {
    let mut last = 0;
    let mut increments = 0;

    let lines: Vec<i32> = read_file().lines()
        .map(|l| l.parse::<i32>().unwrap())
        .collect();

    let mut measurement: Vec<i32> = Vec::new();
    for n in 0 .. lines.len() - 2 {
        let first  = lines[n];
        let second = lines[n + 1];
        let third = lines[n + 2];

        measurement.push(first + second + third);
    }

    for current in measurement {
        if current > last { increments += 1; }
        last = current;
    }

    return increments -1;
}