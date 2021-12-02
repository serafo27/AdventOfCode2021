use common::read_file;

pub fn execute() -> i32 {
    let mut last = 0;
    let mut increments = 0;

    for line in read_file().lines() {
        let current = line.parse::<i32>().unwrap(); // Ignore errors.
        if current > last { increments += 1; }
        last = current;
    }

    return increments -1;
}