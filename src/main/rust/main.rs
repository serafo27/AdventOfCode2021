mod exercises;
mod common;

use exercises::day1::part1::execute as part1;
use exercises::day1::part2::execute as part2;

fn main() {

    let result_part_1 = part1();
    let result_part_2 = part2();

    println!("result part1: {}", result_part_1);
    println!("result part2: {}", result_part_2);
}
