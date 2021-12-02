use std::fs;
use std::path::PathBuf;

pub fn read_file() -> String {
    let mut proj_directory = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    proj_directory.push("src/main/resources/day1/input");

    return fs::read_to_string(proj_directory.as_os_str())
        .expect("Something went wrong reading the file");
}